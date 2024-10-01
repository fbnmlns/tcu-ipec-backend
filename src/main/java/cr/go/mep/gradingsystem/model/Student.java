package cr.go.mep.gradingsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@DiscriminatorValue("STUDENT")
public class Student extends User {
    @ColumnDefault("false")
    private boolean hasCurricularAdaptation;

    @NotNull
    @ManyToMany(mappedBy = "students", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Course> courses;

    @NotNull
    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL)
    private List<AssessmentStudent> assessments;

    @NotNull
    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL)
    private List<AttendanceStudent> attendanceStudents;


    public Student() {
        this.hasCurricularAdaptation = false;
        this.courses = new ArrayList<>();
        this.assessments = new ArrayList<>();
        this.attendanceStudents = new ArrayList<>();
    }
}
