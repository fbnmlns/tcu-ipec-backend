package cr.go.mep.gradingsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "attendances", schema = "institution")
public class Attendance extends Auditable {
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Boolean classOccurred;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @NotNull
    @OneToMany(
            mappedBy = "attendance",
            cascade = CascadeType.ALL)
    private List<AttendanceStudent> attendanceStudents;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String comment;

    @NotNull
    private LocalDate dateOfClass;


    public Attendance(Boolean classOccurred,
                      Course course,
                      String comment,
                      LocalDate dateOfClass) {
        this.classOccurred = classOccurred;
        this.course = course;
        this.attendanceStudents = new ArrayList<>();
        this.comment = comment;
        this.dateOfClass = dateOfClass;
    }
}
