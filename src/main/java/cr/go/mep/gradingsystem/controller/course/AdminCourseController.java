package cr.go.mep.gradingsystem.controller.course;

import cr.go.mep.gradingsystem.dto.*;
import cr.go.mep.gradingsystem.model.Course;
import cr.go.mep.gradingsystem.model.Student;
import cr.go.mep.gradingsystem.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/courses")
@RequiredArgsConstructor
public class AdminCourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody @Valid CourseRequest courseRequest) {
        Long courseId = this.courseService.createCourse(courseRequest);

        if (courseId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(courseId);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long courseId) {
        Course course = this.courseService.getCourseById(courseId);

        CourseResponse courseResponse = new CourseResponse(
                course.getId(),
                course.getType(),
                course.getName(),
                new CourseInstructorResponse(
                        course.getInstructor().getId(),
                        course.getInstructor().getName(),
                        course.getInstructor().getLastName()),
                course.getStartDate(),
                course.getEndDate(),
                course.getMaxCapacity());

        return ResponseEntity.ok(courseResponse);
    }

    @GetMapping
    public ResponseEntity<List<AdminCourseListResponse>> getAdminCourses() {
        List<Course> courses = this.courseService.getAllCourses();

        List<AdminCourseListResponse> adminCourseListResponses = courses.stream()
                .map(course -> new AdminCourseListResponse(
                        course.getId(),
                        course.getType(),
                        course.getName(),
                        new CourseInstructorResponse(
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

    @PutMapping("/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable Long courseId,
                                          @RequestBody @Valid CourseRequest courseRequest) {
        Long updatedCourseId = this.courseService.updateCourse(courseId, courseRequest);

        if (courseId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(updatedCourseId);
    }

    @GetMapping("/{courseId}/available-students")
    public ResponseEntity<List<CourseStudentResponse>> getAvailableStudentsForCourse(@PathVariable Long courseId) {
        List<Student> students = this.courseService.getAllAvailableStudentsForCourse(courseId);

        List<CourseStudentResponse> courseStudentResponses = students.stream()
                .map(student -> new CourseStudentResponse(
                        student.getId(),
                        student.getName(),
                        student.getLastName(),
                        student.getEmail()))
                .toList();

        return ResponseEntity.ok(courseStudentResponses);
    }

    @PostMapping("/{courseId}/students")
    public ResponseEntity<?> addStudentToCourse(@PathVariable Long courseId,
                                                @RequestBody @Valid Long studentId) {
        Long updatedCourseId = this.courseService.addStudentToCourse(courseId, studentId);

        if (updatedCourseId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(updatedCourseId);
    }
}
