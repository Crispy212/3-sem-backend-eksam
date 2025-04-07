package app.rest;

import app.controllers.SkiLessonController;
import app.dtos.SkiLessonDTO;
import io.javalin.apibuilder.EndpointGroup;

import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.*;

public class SkiLessonRoutes {
    private final SkiLessonController controller;

    public SkiLessonRoutes(SkiLessonController controller) {
        this.controller = controller;
    }

    public EndpointGroup getRoutes() {
        return () -> {
            get(ctx -> ctx.json(controller.getAllSkiLessons()));

            post(ctx -> {
                SkiLessonDTO dto = ctx.bodyAsClass(SkiLessonDTO.class);
                ctx.json(controller.addSkiLesson(dto));
            }); // POST /skilessons

            path("populate", () -> post(ctx -> {
                controller.populateDatabase(null);
                ctx.status(201).result("Populated");
            }));

            path("{id}", () -> {
                get(ctx -> {
                    long id = Long.parseLong(ctx.pathParam("id"));
                    ctx.json(controller.getSkiLessonById(id));
                });

                put(ctx -> {
                    long id = Long.parseLong(ctx.pathParam("id"));
                    SkiLessonDTO dto = ctx.bodyAsClass(SkiLessonDTO.class);
                    ctx.json(controller.updateSkiLesson(id, dto));
                });

                delete(ctx -> {
                    long id = Long.parseLong(ctx.pathParam("id"));
                    controller.deleteSkiLesson(id);
                    ctx.status(204);
                });
            });

            path("{lessonId}/instructors/{instructorId}", () -> {
                put(ctx -> {
                    long lessonId = Long.parseLong(ctx.pathParam("lessonId"));
                    long instructorId = Long.parseLong(ctx.pathParam("instructorId"));
                    controller.assignInstructorToLesson(lessonId, instructorId);
                    ctx.status(204);
                });
            });

            path("filter/{level}", () -> {
                get(ctx -> {
                    String level = ctx.pathParam("level");
                    ctx.json(controller.getLessonsByLevel(level));
                });
            });

            path("summary/price", () -> {
                get(ctx -> ctx.json(controller.getTotalLessonPricesByInstructor()));
            });

            path("{id}/instruction-duration", () -> {
                get(ctx -> {
                    long id = Long.parseLong(ctx.pathParam("id"));
                    int totalDuration = controller.getTotalInstructionDuration(id);
                    ctx.json(Map.of("lessonId", id, "totalDuration", totalDuration));
                });
            });

            path("instructions/{level}", () -> {
                get(ctx -> {
                    String level = ctx.pathParam("level");
                    ctx.json(controller.getInstructionsByLevel(level));
                });
            });


        };
    }
}
