package app.daos;

import app.exceptions.ApiException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

import app.entities.Instructor;

public class InstructorDAO
{
    private static EntityManagerFactory emf;
    private static InstructorDAO instance;

    // Singleton
    public InstructorDAO()
    {
    }

    public static InstructorDAO getInstance(EntityManagerFactory _emf)
    {
        if (emf == null)
        {
            emf = _emf;
            instance = new InstructorDAO();
        }
        return instance;
    }

    public Instructor create(Instructor instructor)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.persist(instructor);
            em.getTransaction().commit();
            return instructor;
        } catch (Exception e)
        {
            throw new ApiException(401, "Error creating guide", e);
        }
    }

    public Instructor read(Long id)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            Instructor instructor = em.find(Instructor.class, id);
            if (instructor == null)
            {
                throw new NullPointerException();
            }
            return instructor;
        } catch (Exception e)
        {
            throw new ApiException(404, "Error guide not found", e);
        }
    }

    public Instructor readByName(String firstName, String lastName)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            TypedQuery<Instructor> query = em.createQuery(
                    "SELECT i FROM Instructor i WHERE i.firstName = :firstName AND i.lastName = :lastName",
                    Instructor.class);
            query.setParameter("firstName", firstName);
            query.setParameter("lastName", lastName);
            return query.getSingleResult();
        } catch (Exception e)
        {
            throw new ApiException(404, "Error: Instructor not found", e);
        }
    }

    public List<Instructor> readAll()
    {
        try (EntityManager em = emf.createEntityManager())
        {
            return em.createQuery("SELECT i FROM Instructor i ORDER BY i.id", Instructor.class).getResultList();
        } catch (Exception e)
        {
            throw new ApiException(401, "Error finding guides", e);
        }
    }

    public Instructor update(Instructor instructor)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            Instructor updatedInstructor = em.merge(instructor);
            em.getTransaction().commit();
            return updatedInstructor;
        } catch (Exception e)
        {
            throw new ApiException(401, "Error updating instructor", e);
        }
    }

    public void delete(Long id)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            try
            {
                em.getTransaction().begin();
                Instructor instructor = em.find(Instructor.class, id);
                if (instructor == null)
                {
                    em.getTransaction().rollback();
                    throw new NullPointerException();
                }
                em.remove(instructor);
                em.getTransaction().commit();
            } catch (Exception e)
            {
                throw new ApiException(401, "Error deleting Instructor", e);
            }
        }
    }
}