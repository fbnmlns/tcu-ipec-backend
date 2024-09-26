package cr.go.mep.gradingsystem.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMINISTRATOR("administrator"),
    INSTRUCTOR("instructor"),
    STUDENT("student");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }
}
