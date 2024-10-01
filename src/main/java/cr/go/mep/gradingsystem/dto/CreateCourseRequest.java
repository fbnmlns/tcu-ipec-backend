package cr.go.mep.gradingsystem.dto;

import cr.go.mep.gradingsystem.enums.CourseType;

import java.time.LocalDateTime;

public record CreateCourseRequest(
        CourseType type,
        String name,
        Long instructorId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        int maxCapacity
) {}

