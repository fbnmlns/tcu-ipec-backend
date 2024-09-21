package cr.go.mep.gradingsystem.model;

import cr.go.mep.gradingsystem.enums.AssistanceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "attendance_students")
public class AttendanceStudent extends Auditable {
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AssistanceStatus status;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String justification;

    @NotBlank
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendance_id")
    private Attendance attendance;


    public AttendanceStudent(AssistanceStatus status,
                             String justification,
                             Student student,
                             Attendance attendance) {
        this.status = status;
        this.justification = justification;
        this.student = student;
        this.attendance = attendance;
    }
}
