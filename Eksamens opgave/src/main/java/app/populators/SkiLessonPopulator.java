package app.populators;

import app.daos.SkiLessonDAO;
import app.entities.SkiLesson;
import app.enums.SkillLevel;
import jakarta.persistence.EntityManagerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SkiLessonPopulator
{
    public static void populate(EntityManagerFactory emf)
    {
        SkiLessonDAO skiLessonDAO = SkiLessonDAO.getInstance(emf);

        SkiLesson lesson1 = new SkiLesson(
                "Mountain Slopes",
                LocalDate.of(2025, 4, 21),
                LocalDate.of(2025, 4, 30),
                SkillLevel.BEGINNER,
                new BigDecimal("2400"),
                46.8320, // latitude
                8.4048   // longitude
        );

        SkiLesson lesson2 = new SkiLesson(
                "Snow Safari",
                LocalDate.of(2025, 5, 10),
                LocalDate.of(2025, 5, 15),
                SkillLevel.INTERMEDIATE,
                new BigDecimal("3200"),
                47.0502,
                8.3093
        );

        SkiLesson lesson3 = new SkiLesson(
                "Alpine Pro Challenge",
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 7),
                SkillLevel.ADVANCED,
                new BigDecimal("4100"),
                46.9782,
                9.2416
        );

        skiLessonDAO.createSkiLesson(lesson1);
        skiLessonDAO.createSkiLesson(lesson2);
        skiLessonDAO.createSkiLesson(lesson3);
    }
}