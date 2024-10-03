package cr.go.mep.gradingsystem.service;

import cr.go.mep.gradingsystem.dto.CreateCourseRequest;
import cr.go.mep.gradingsystem.model.Course;
import cr.go.mep.gradingsystem.model.Instructor;
import cr.go.mep.gradingsystem.repository.CourseRepository;
import cr.go.mep.gradingsystem.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    public Long createCourse(CreateCourseRequest createCourseRequest) {
        Optional<Instructor> optionalInstructor = this.instructorRepository.findById(createCourseRequest.instructorId());

        if (optionalInstructor.isPresent()) {
            Instructor instructor = optionalInstructor.get();

            Course newCourse = new Course(
                    createCourseRequest.type(),
                    createCourseRequest.name(),
                    instructor,
                    createCourseRequest.startDate(),
                    createCourseRequest.endDate(),
                    createCourseRequest.maxCapacity());

            return this.courseRepository.save(newCourse).getId();
        } else {
            throw new InvalidConfigurationPropertyValueException(
                    "instructor id",
                    createCourseRequest.instructorId(),
                    "resource does not exist");
        }
    }

    public List<Course> getAllCourses() {
        return this.courseRepository.findAll();
    }
}
