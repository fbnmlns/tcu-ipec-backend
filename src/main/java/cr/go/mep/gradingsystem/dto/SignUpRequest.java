package cr.go.mep.gradingsystem.dto;

import cr.go.mep.gradingsystem.enums.UserRole;

public record SignUpRequest(
        String name,
        String lastName,
        String email,
        String username,
        String password,
        UserRole role
) {}
