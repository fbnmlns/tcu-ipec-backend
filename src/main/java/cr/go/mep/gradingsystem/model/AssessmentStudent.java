package cr.go.mep.gradingsystem.model;

import cr.go.mep.gradingsystem.enums.AssessmentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "assessment_students")
public class AssessmentStudent extends Auditable {
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PositiveOrZero
    private double score;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AssessmentStatus status;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String feedback;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;


    public AssessmentStudent(double score,
                             AssessmentStatus status,
                             String feedback,
                             Student student,
                             Assessment assessment) {
        this.score = score;
        this.status = status;
        this.feedback = feedback;
        this.student = student;
        this.assessment = assessment;
    }
}
