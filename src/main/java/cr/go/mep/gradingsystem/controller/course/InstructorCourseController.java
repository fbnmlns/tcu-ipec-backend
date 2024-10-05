package cr.go.mep.gradingsystem.controller.course;

import cr.go.mep.gradingsystem.dto.InstructorCourseListResponse;
import cr.go.mep.gradingsystem.model.Course;
import cr.go.mep.gradingsystem.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("instructor/courses")
@RequiredArgsConstructor
public class InstructorCourseController {
    private final CourseService courseService;

    @GetMapping("/instructor")
    public ResponseEntity<List<InstructorCourseListResponse>> getInstructorCourses(
            @RequestBody @Valid Long instructorId) {
        List<Course> courses = this.courseService.getAllCoursesByInstructorId(instructorId);

        List<InstructorCourseListResponse> instructorCourseListResponses = courses.stream()
                .map(course -> new InstructorCourseListResponse(
                        course.getId(),
                        course.getType(),
                        course.getName()))
                .toList();

        return ResponseEntity.ok(instructorCourseListResponses);
    }
}
