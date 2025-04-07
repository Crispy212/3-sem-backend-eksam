package app.entities;

import app.enums.SkillLevel;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class SkiLesson
{
    public SkiLesson(String name, LocalDate startTime, LocalDate endTime, SkillLevel level, BigDecimal price, double latitude, double longitude) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.level = level;
        this.price = price;
    }

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Setter
    @Column(name = "Time")
    private LocalDate startTime;
    @Setter
    private LocalDate endTime;
    @Setter
    @Column(name = "Locaion")
    private double longitude;
    @Setter
    private double latitude;
    @Setter
    @Column(name = "Lesson_name")
    private String name;
    @Setter
    @Column(name = "Price")
    private BigDecimal price;
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "Type_of_lesson")
    private SkillLevel level;
    @Setter
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

}
