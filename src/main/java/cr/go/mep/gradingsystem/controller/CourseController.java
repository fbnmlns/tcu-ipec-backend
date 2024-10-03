package cr.go.mep.gradingsystem.controller;

import cr.go.mep.gradingsystem.dto.AdminCourseListInstructorResponse;
import cr.go.mep.gradingsystem.dto.AdminCourseListResponse;
import cr.go.mep.gradingsystem.dto.CreateCourseRequest;
import cr.go.mep.gradingsystem.model.Course;
import cr.go.mep.gradingsystem.repository.CourseRepository;
import cr.go.mep.gradingsystem.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final CourseRepository courseRepository;
    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody @Valid CreateCourseRequest createCourseRequest) {
        Long courseId = this.courseService.createCourse(createCourseRequest);

        if (courseId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(courseId);
    }

    @GetMapping
    public ResponseEntity<List<AdminCourseListResponse>> getAdminCourses() {
        List<Course> courses = this.courseService.getAllCourses();

        List<AdminCourseListResponse> adminCourseListResponses = courses.stream()
                .map(course -> new AdminCourseListResponse(
                        course.getId(),
                        course.getType(),
                        course.getName(),
                        new AdminCourseListInstructorResponse(
                                course.getInstructor().getId(),
                                course.getInstructor().getName(),
                                course.getInstructor().getLastName()),
                        course.getStartDate(),
                        course.getEndDate(),
                        course.getStudents().size(),
                        course.getMaxCapacity()))
                .toList();

        return ResponseEntity.ok(adminCourseListResponses);
    }
}
