package app.daos;

import app.dtos.SkiLessonDTO;
import java.util.Set;

public interface ISkiLessionInstructorDAO
{
    void addInstructorToSkiLesson(int lessonId, int instructorId);
    Set<SkiLessonDTO> getSkiLessonsByInstructor(int instructorId);
}
