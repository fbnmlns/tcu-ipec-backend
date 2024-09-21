package cr.go.mep.gradingsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "rubric_criterion")
public class RubricCriterion extends BaseEntity {
    @NotBlank
    @Setter(AccessLevel.PROTECTED)
    private String title;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluation_rubric_id")
    private EvaluationRubric evaluationRubric;

    @NotNull
    @OneToMany(
            mappedBy = "rubricCriterion",
            cascade = CascadeType.ALL)
    private List<AchievementLevel> levels;


    public RubricCriterion(String title, EvaluationRubric evaluationRubric) {
        this.title = title;
        this.evaluationRubric = evaluationRubric;
        this.levels = new ArrayList<>();
    }
}
