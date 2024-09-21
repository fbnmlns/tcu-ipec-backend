package cr.go.mep.gradingsystem.model;

import cr.go.mep.gradingsystem.enums.AssessmentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
@Table(name = "assessments")
public class Assessment extends Auditable {
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AssessmentType type;

    @PositiveOrZero
    private double percentage;

    @PositiveOrZero
    private double totalPoints;

    @NotNull
    @FutureOrPresent
    private LocalDateTime assessmentDate;

    @NotNull
    @OneToOne(
            mappedBy = "assessment",
            cascade = CascadeType.ALL)
    private EvaluationRubric evaluationRubric;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @NotNull
    @OneToMany(
            mappedBy = "assessment",
            cascade = CascadeType.ALL)
    private List<AssessmentStudent> assessmentStudents;

    public Assessment(String name,
                      AssessmentType type,
                      double percentage,
                      double totalPoints,
                      LocalDateTime assessmentDate,
                      EvaluationRubric evaluationRubric,
                      Course course) {
        this.name = name;
        this.type = type;
        this.percentage = percentage;
        this.totalPoints = totalPoints;
        this.assessmentDate = assessmentDate;
        this.evaluationRubric = evaluationRubric;
        this.course = course;
        this.assessmentStudents = new ArrayList<>();
    }
}
