package app.dtos;

import app.entities.SkiLesson;
import app.enums.SkillLevel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SkiLessonDTO
{

    private long id;
    @JsonProperty("trip_id")
    private String name;
    private LocalDate startTime;
    private LocalDate endTime;
    private double longitude;
    private double latitude;
    private BigDecimal price;
    private SkillLevel level;
    private InstructorDTO guide;


    @Setter
    private List<LessonAPIDTO> instructions;

    public SkiLessonDTO(String name, LocalDate startTime, LocalDate endTime, double longitude, double latitude, BigDecimal price, SkillLevel level)
    {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.longitude = longitude;
        this.latitude = latitude;
        this.price = price;
        this.level = level;
    }

    public SkiLessonDTO(SkiLesson skiLesson)
    {
        this.id = skiLesson.getId();
        this.name = skiLesson.getName();
        this.startTime = skiLesson.getStartTime();
        this.endTime = skiLesson.getEndTime();
        this.longitude = skiLesson.getLongitude();
        this.latitude = skiLesson.getLatitude();
        this.price = skiLesson.getPrice();
        this.level = skiLesson.getLevel();
        if (skiLesson.getInstructor() != null)
        {
            this.guide = new InstructorDTO(skiLesson.getInstructor(), false);
        }
    }

    public SkiLessonDTO(SkiLesson skiLesson, boolean includeDetails)
    {
        this.id = skiLesson.getId();
        this.name = skiLesson.getName();
        this.startTime = skiLesson.getStartTime();
        this.endTime = skiLesson.getEndTime();
        this.longitude = skiLesson.getLongitude();
        this.latitude = skiLesson.getLatitude();
        this.price = skiLesson.getPrice();
        this.level = skiLesson.getLevel();

        if (includeDetails && skiLesson.getInstructor() != null)
        {
            this.guide = new InstructorDTO(skiLesson.getInstructor(), false);
        }
    }


}
