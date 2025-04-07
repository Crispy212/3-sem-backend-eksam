package app.dtos;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class LessonAPIDTO {
    private String title;
    private String description;
    private String level;
    private int durationMinutes;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
