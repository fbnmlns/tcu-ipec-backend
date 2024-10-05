package cr.go.mep.gradingsystem.service;

import cr.go.mep.gradingsystem.dto.CourseRequest;
import cr.go.mep.gradingsystem.model.Course;
import cr.go.mep.gradingsystem.model.Instructor;
import cr.go.mep.gradingsystem.model.Student;
import cr.go.mep.gradingsystem.repository.CourseRepository;
import cr.go.mep.gradingsystem.repository.InstructorRepository;
import cr.go.mep.gradingsystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;

    public Long createCourse(CourseRequest courseRequest) {
        Instructor instructor = this.instructorRepository.findById(courseRequest.instructorId())
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException(
                        "instructor id",
                        courseRequest.instructorId(),
                        "resource does not exist"));

        Course newCourse = new Course(
                courseRequest.type(),
                courseRequest.name(),
                instructor,
                courseRequest.startDate(),
                courseRequest.endDate(),
                courseRequest.maxCapacity());

        return this.courseRepository.save(newCourse).getId();
    }

    public Course getCourseById(Long courseId) {
        return this.courseRepository.findById(courseId)
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException(
                        "course id",
                        courseId,
                        "resource does not exist"));
    }

    public List<Course> getAllCourses() {
        return this.courseRepository.findAll();
    }

    public List<Course> getAllCoursesByInstructorId(Long instructorId) {
        return this.courseRepository.findAllByInstructorId(instructorId);
    }

    public Long updateCourse(Long courseId, CourseRequest courseRequest) {
        Course course = this.courseRepository.findById(courseId)
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException(
                        "course id",
                        courseId,
                        "resource does not exist"));

        Instructor instructor = this.instructorRepository.findById(courseRequest.instructorId())
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException(
                        "instructor id",
                        courseRequest.instructorId(),
                        "resource does not exist"));


        course.setType(courseRequest.type());
        course.setName(courseRequest.name());
        course.setInstructor(instructor);
        course.setStartDate(courseRequest.startDate());
        course.setEndDate(courseRequest.endDate());
        course.setMaxCapacity(courseRequest.maxCapacity());

        return this.courseRepository.save(course).getId();
    }

    public Long addStudentToCourse(Long courseId, Long studentId) {
        Course course = this.courseRepository.findById(courseId)
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException(
                        "course id",
                        courseId,
                        "resource does not exist"));

        Student student = this.studentRepository.findById(studentId)
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException(
                        "student id",
                        studentId,
                        "resource does not exist"));

        course.getStudents().add(student);

        return this.courseRepository.save(course).getId();
    }

    public List<Student> getAllAvailableStudentsForCourse(Long courseId) {
        Course course = this.courseRepository.findById(courseId)
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException(
                        "course id",
                        courseId,
                        "resource does not exist"));

        return this.studentRepository.findStudentsNotInCourse(course);
    }
}
