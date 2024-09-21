package cr.go.mep.gradingsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "achievement_levels")
public class AchievementLevel extends BaseEntity {
    @NotBlank
    private String name;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String description;

    @Positive
    private int score;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rubric_criterion_id")
    private RubricCriterion rubricCriterion;

    public AchievementLevel(String name,
                            String description,
                            int score,
                            RubricCriterion rubricCriterion) {
        this.name = name;
        this.description = description;
        this.score = score;
        this.rubricCriterion = rubricCriterion;
    }
}
