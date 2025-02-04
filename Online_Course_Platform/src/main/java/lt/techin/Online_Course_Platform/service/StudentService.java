package lt.techin.Online_Course_Platform.service;

import lt.techin.Online_Course_Platform.dto.StudentDTO;
import lt.techin.Online_Course_Platform.model.Course;
import lt.techin.Online_Course_Platform.model.Student;
import lt.techin.Online_Course_Platform.repository.CourseRepository;
import lt.techin.Online_Course_Platform.repository.StudentRepository;
import lt.techin.Online_Course_Platform.validation.EmailExistsException;
import lt.techin.Online_Course_Platform.validation.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    private CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository,CourseRepository courseRepository){
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(StudentDTO dto) {
        if(studentRepository.existsByEmail(dto.email())) {
            throw new EmailExistsException("The email " + dto.email() + " already exists");
        }
        Student student = new Student();
        student.setName(dto.name());
        student.setDob(dto.dob());
        student.setEmail(dto.email());

        studentRepository.save(student);

        return student;
    }

    public String addCourseToStudent(long studentId,long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student with id '" + studentId + "' does not exist"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course with id '" + courseId + "' does not exist"));

        student.getCourses().add(course);
        course.getStudents().add(student);

        studentRepository.save(student);
        courseRepository.save(course);

        return "Student successfully added to a course";
    }

}
