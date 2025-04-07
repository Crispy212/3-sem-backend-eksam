package app.rest;

import app.dtos.ErrorMessage;
import app.controllers.SkiLessonController;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.*;


public class Routes
{
    // declare static controllers here
    private static Logger logger = LoggerFactory.getLogger(Routes.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static EndpointGroup getRoutes(EntityManagerFactory emf) {
        SkiLessonController controller = new SkiLessonController(emf);
        SkiLessonRoutes skiLessonRoutes = new SkiLessonRoutes(controller);

        return () -> {
            path("skilessons", skiLessonRoutes.getRoutes());

        };
    }
}