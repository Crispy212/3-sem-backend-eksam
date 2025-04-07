package app.populators;

import app.daos.InstructorDAO;
import app.entities.Instructor;
import app.security.entities.*;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

public class InstructorPopulator
{
    public static void poulate(EntityManagerFactory emf)
    {
        InstructorDAO instructorDAO = InstructorDAO.getInstance(emf);

        Instructor instructor1 = new Instructor("Hilda","Johanssen", "HildaSKI@skiresort.com", 50605060,9);
        Instructor instructor2 = new Instructor("Mads","Olsen", "HuskyDude@skiresort.com", 32938457,4);
        Instructor instructor3 = new Instructor("Mike","Mcmorse", "OldManMike@skiresort.com", 11485612,16);

        instructorDAO.create(instructor1);
        instructorDAO.create(instructor3);
        instructorDAO.create(instructor2);

        System.out.println("Instructors have been populated.");
    }
}
