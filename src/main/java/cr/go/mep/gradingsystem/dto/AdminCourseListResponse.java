package cr.go.mep.gradingsystem.dto;

import cr.go.mep.gradingsystem.enums.CourseType;

import java.time.LocalDateTime;

public record AdminCourseListResponse(
        Long courseId,
        CourseType type,
        String name,
        AdminCourseListInstructorResponse instructor,
        LocalDateTime startDate,
        LocalDateTime endDate,
        int numberOfRegisteredStudents,
        int maxCapacity
) {}
