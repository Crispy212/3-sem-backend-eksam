package app.dtos;

import app.entities.Instructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@ToString
@AllArgsConstructor
@Builder
@JsonIgnoreProperties
public class InstructorDTO
{

    @JsonProperty("trip_id")
    private String firstName;
    private String lastName;
    private String Email;
    private int phoneNumber;
    private int yearsOfExperience;
    private Set<SkiLessonDTO> trips;

    public InstructorDTO(Instructor instructor, boolean includeSkilessons) {
        this.firstName = instructor.getFirstName();
        this.lastName = instructor.getLastName();

        if (includeSkilessons && instructor.getLessons() != null) {
            this.trips = instructor.getLessons()
                    .stream()
                    .map(skiLessons -> new SkiLessonDTO(skiLessons, false))
                    .collect(Collectors.toSet());
        }
    }
}
