package cr.go.mep.gradingsystem.controller;

import cr.go.mep.gradingsystem.dto.CreateCourseRequest;
import cr.go.mep.gradingsystem.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

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
}
