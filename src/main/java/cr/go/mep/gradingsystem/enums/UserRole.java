package cr.go.mep.gradingsystem.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMINISTRATOR("administrator"),
    INSTRUCTOR("instructor"),
    STUDENT("student");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }
}
