package cr.go.mep.gradingsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Student extends User {
    @NotNull
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


    public Student(boolean hasCurricularAdaptation) {
        this.hasCurricularAdaptation = hasCurricularAdaptation;
        this.courses = new ArrayList<>();
        this.assessments = new ArrayList<>();
        this.attendanceStudents = new ArrayList<>();
    }
}
