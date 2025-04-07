package app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Instructor
{
    public Instructor(String firstName, String lastName, String email, int phoneNumber, int yearsOfExperience)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.yearsOfExperience = yearsOfExperience;
    }

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "instructor name")
    private String firstName;
    private String lastName;
    @Column(name = "instructor info")
    private String email;
    private int phoneNumber;
    private int yearsOfExperience;

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private Set<SkiLesson> lessons;
}