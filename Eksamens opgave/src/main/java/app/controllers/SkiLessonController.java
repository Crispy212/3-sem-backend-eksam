package app.controllers;

import app.daos.SkiLessonDAO;
import app.dtos.InstructorPriceDTO;
import app.dtos.LessonAPIDTO;
import app.dtos.SkiLessonDTO;
import app.dtos.InstructorPriceDTO;
import app.entities.SkiLesson;
import jakarta.persistence.EntityManagerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SkiLessonController {

    private final SkiLessonDAO skiLessonDAO;

    public SkiLessonController(EntityManagerFactory emf) {
        this.skiLessonDAO = SkiLessonDAO.getInstance(emf);
    }

    public List<SkiLessonDTO> getAllSkiLessons() {
        return skiLessonDAO.readAllSkiLessons().stream()
                .map(SkiLessonDTO::new)
                .collect(Collectors.toList());
    }

    public SkiLessonDTO getSkiLessonById(Long id) {
        SkiLesson lesson = skiLessonDAO.readSkiLessonById(id);
        if (lesson == null) return null;

        SkiLessonDTO dto = new SkiLessonDTO(lesson);
        dto.setInstructions(getInstructionsByLevel(lesson.getLevel().name()));
        return dto;
    }

    public SkiLessonDTO addSkiLesson(SkiLessonDTO dto) {
        SkiLesson lesson = new SkiLesson(
                dto.getName(),
                dto.getStartTime(),
                dto.getEndTime(),
                dto.getLevel(),
                dto.getPrice(),
                dto.getLatitude(),
                dto.getLongitude()
        );
        return new SkiLessonDTO(skiLessonDAO.createSkiLesson(lesson));
    }

    public SkiLessonDTO updateSkiLesson(Long id, SkiLessonDTO dto) {
        SkiLesson lesson = skiLessonDAO.readSkiLessonById(id);
        if (lesson == null) return null;

        lesson.setName(dto.getName());
        lesson.setStartTime(dto.getStartTime());
        lesson.setEndTime(dto.getEndTime());
        lesson.setLevel(dto.getLevel());
        lesson.setPrice(dto.getPrice());
        lesson.setLatitude(dto.getLatitude());
        lesson.setLongitude(dto.getLongitude());

        return new SkiLessonDTO(skiLessonDAO.updateSkiLesson(lesson));
    }

    public void deleteSkiLesson(Long id) {
        skiLessonDAO.deleteSkiLesson(id);
    }

    public void assignInstructorToLesson(Long lessonId, Long instructorId) {
        skiLessonDAO.addInstructorToSkiLesson(lessonId.intValue(), instructorId.intValue());
    }

    public void populateDatabase(EntityManagerFactory emf) {
    }

    //getting info from external api
    public List<LessonAPIDTO> getInstructionsByLevel(String level) {
        try {
            String apiUrl = "https://apiprovider.cphbusinessapps.dk/skilesson/" + level.toLowerCase();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            //return mapper.readValue(response.body(), new TypeReference<>() {});
            LessonAPIDTO instruction = mapper.readValue(response.body(), LessonAPIDTO.class);
            return List.of(instruction);

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    //for finding duration on lesson based on level
    public int getTotalInstructionDuration(Long lessonId) {
        SkiLesson lesson = skiLessonDAO.readSkiLessonById(lessonId);
        if (lesson == null) return 0;

        List<LessonAPIDTO> instructions = getInstructionsByLevel(lesson.getLevel().name());
        return instructions.stream().mapToInt(LessonAPIDTO::getDurationMinutes).sum();
    }

    //for finding price
    public List<InstructorPriceDTO> getTotalLessonPricesByInstructor() {
        return skiLessonDAO.readAllSkiLessons().stream()
                .filter(lesson -> lesson.getInstructor() != null)
                .collect(Collectors.groupingBy(
                        lesson -> lesson.getInstructor().getId(),
                        Collectors.summingDouble(l -> l.getPrice().doubleValue())
                ))
                .entrySet().stream()
                .map(entry -> new InstructorPriceDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    //filter lessons by level
    public List<SkiLessonDTO> getLessonsByLevel(String level) {
        return skiLessonDAO.readAllSkiLessons().stream()
                .filter(lesson -> lesson.getLevel().name().equalsIgnoreCase(level))
                .map(SkiLessonDTO::new)
                .collect(Collectors.toList());
    }
}
