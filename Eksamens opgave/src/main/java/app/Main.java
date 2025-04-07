package app;

import app.config.HibernateConfig;
import app.populators.InstructorPopulator;
import app.populators.SkiLessonPopulator;
import app.rest.ApplicationConfig;
import app.rest.Routes;
import app.security.rest.SecurityRoutes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Main
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

        setupDatabase(emf);

        ApplicationConfig
                .getInstance()
                .initiateServer()
                .securityCheck()
                .setRoute(Routes.getRoutes(emf))
                .setRoute(SecurityRoutes.getRoutes(emf))
                .handleException()
                .startServer(7070);

    }

    private static void setupDatabase(EntityManagerFactory emf)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            InstructorPopulator.poulate(emf);
            SkiLessonPopulator.populate(emf);
        }
    }

}
