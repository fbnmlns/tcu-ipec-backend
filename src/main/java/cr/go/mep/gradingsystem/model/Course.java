package cr.go.mep.gradingsystem.model;

import cr.go.mep.gradingsystem.enums.CourseType;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course extends Auditable {
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @ManyToOne
    private Instructor instructor;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CourseType type;

    @NotNull
    @OneToMany(mappedBy = "course")
    List<Assessment> assessments;

    @FutureOrPresent
    private LocalDateTime startDate;

    @FutureOrPresent
    private LocalDateTime endDate;

    @Positive
    private int maxCapacity;

    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    @OneToMany(mappedBy = "course")
    private List<Attendance> attendance;


    public Course(CourseType type,
                  String name,
                  Instructor instructor,
                  LocalDateTime startDate,
                  LocalDateTime endDate,
                  int maxCapacity) {
        this.type = type;
        this.name = name;
        this.instructor = instructor;
        this.assessments = new ArrayList<>();
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxCapacity = maxCapacity;
        this.students = new ArrayList<>();
        this.attendance = new ArrayList<>();
    }
}
