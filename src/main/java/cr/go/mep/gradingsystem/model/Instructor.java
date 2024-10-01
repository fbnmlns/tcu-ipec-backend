package cr.go.mep.gradingsystem.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("INSTRUCTOR")
public class Instructor extends User {
    @NotNull
    @OneToMany(
            mappedBy = "instructor",
            cascade = CascadeType.ALL)
    List<Course> courses;


    public Instructor() {
        this.courses = new ArrayList<>();
    }
}
