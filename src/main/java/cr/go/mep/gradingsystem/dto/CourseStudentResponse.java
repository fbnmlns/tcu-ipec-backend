package cr.go.mep.gradingsystem.dto;

public record CourseStudentResponse(
        Long studentId,
        String name,
        String lastName,
        String email
) {}
