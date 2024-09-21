package cr.go.mep.gradingsystem.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Instructor extends User {
    @NotNull
    @Setter(AccessLevel.PROTECTED)
    @OneToMany(
            mappedBy = "instructor",
            cascade = CascadeType.ALL)
    List<Course> courses;
}
