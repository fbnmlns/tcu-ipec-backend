package cr.go.mep.gradingsystem.dto;

import cr.go.mep.gradingsystem.enums.CourseType;

public record InstructorCourseListResponse(
        Long courseId,
        CourseType type,
        String name
) {}
