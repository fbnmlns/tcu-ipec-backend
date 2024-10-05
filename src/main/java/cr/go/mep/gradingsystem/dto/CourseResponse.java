package cr.go.mep.gradingsystem.dto;

import cr.go.mep.gradingsystem.enums.CourseType;

import java.time.LocalDateTime;

public record CourseResponse(
        Long courseId,
        CourseType type,
        String name,
        CourseInstructorResponse instructor,
        LocalDateTime startDate,
        LocalDateTime endDate,
        int maxCapacity
) {}
