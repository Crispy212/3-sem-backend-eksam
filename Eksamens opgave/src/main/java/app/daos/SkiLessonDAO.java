package app.daos;

import app.dtos.SkiLessonDTO;
import app.entities.Instructor;
import java.util.List;
import app.entities.SkiLesson;
import jakarta.persistence.*;

import java.util.Set;
import java.util.stream.Collectors;

public class SkiLessonDAO implements ISkiLessionInstructorDAO {
    private static EntityManagerFactory emf;
    private static SkiLessonDAO instance;

    public SkiLessonDAO() {}

    public static SkiLessonDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            instance = new SkiLessonDAO();
            emf = _emf;
        }
        return instance;
    }

    public SkiLesson createSkiLesson(SkiLesson skiLesson) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(skiLesson);
            tx.commit();
            return skiLesson;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Error creating skiLesson", e);
        } finally {
            em.close();
        }
    }

    public SkiLesson readSkiLessonById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(SkiLesson.class, id);
        } finally {
            em.close();
        }
    }

    public List<SkiLesson> readAllSkiLessons() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<SkiLesson> query = em.createQuery("SELECT s FROM SkiLesson s", SkiLesson.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public SkiLesson updateSkiLesson(SkiLesson skiLesson) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            SkiLesson updateTrip = em.merge(skiLesson);
            tx.commit();
            return updateTrip;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Error updating skiLesson", e);
        } finally {
            em.close();
        }
    }

    public void deleteSkiLesson(long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            SkiLesson skiLesson = em.find(SkiLesson.class, id);
            if (skiLesson != null) {
                em.remove(skiLesson);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Error deleting skiLesson", e);
        } finally {
            em.close();
        }
    }
    @Override
    public void addInstructorToSkiLesson(int skiLessonId, int instructorId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            SkiLesson skiLesson = em.find(SkiLesson.class, skiLessonId);
            Instructor instructor = em.find(Instructor.class, instructorId);

            if (skiLesson == null || instructor == null) {
                throw new IllegalArgumentException("Instructor or SkiLesson not found");
            }
            skiLesson.setInstructor(instructor);
            em.merge(skiLesson);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Error assigning instructor to ski lesson", e);
        } finally {
            em.close();
        }
    }


    @Override
    public Set<SkiLessonDTO> getSkiLessonsByInstructor(int instructorId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<SkiLesson> query = em.createQuery(
                    "SELECT s FROM SkiLesson s WHERE s.instructor.id = :instructorId",
                    SkiLesson.class
            );
            query.setParameter("instructorId", instructorId);
            List<SkiLesson> skiLessons = query.getResultList();

            return skiLessons.stream()
                    .map(SkiLessonDTO::new)
                    .collect(Collectors.toSet());
        } finally {
            em.close();
        }
    }

}