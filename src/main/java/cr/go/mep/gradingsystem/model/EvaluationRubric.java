package cr.go.mep.gradingsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "evaluation_rubrics")
public class EvaluationRubric extends Auditable {
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;

    @NotNull
    @OneToMany(
            mappedBy = "evaluationRubric",
            cascade = CascadeType.ALL)
    private List<RubricCriterion> criteria;


    public EvaluationRubric(Assessment assessment) {
        this.assessment = assessment;
        this.criteria = new ArrayList<>();
    }
}
