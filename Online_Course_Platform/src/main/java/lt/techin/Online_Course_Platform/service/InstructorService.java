package lt.techin.Online_Course_Platform.service;


import lt.techin.Online_Course_Platform.dto.CourseMapper;
import lt.techin.Online_Course_Platform.dto.InstructorDTO;
import lt.techin.Online_Course_Platform.dto.InstructorMapper;
import lt.techin.Online_Course_Platform.model.Course;
import lt.techin.Online_Course_Platform.model.Instructor;
import lt.techin.Online_Course_Platform.repository.CourseRepository;
import lt.techin.Online_Course_Platform.repository.InstructorRepository;
import lt.techin.Online_Course_Platform.validation.EmailExistsException;
import lt.techin.Online_Course_Platform.validation.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

  private InstructorRepository instructorRepository;
  private CourseRepository courseRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public InstructorService(InstructorRepository instructorRepository, CourseRepository courseRepository, PasswordEncoder passwordEncoder) {
    this.instructorRepository = instructorRepository;
    this.courseRepository = courseRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public String encodePassword(String rawPassword) {
    return passwordEncoder.encode(rawPassword);
  }

  public boolean matchesPassword(String rawPassword, String encodedPassword) {
    return passwordEncoder.matches(rawPassword, encodedPassword);
  }

  public Instructor postInstructorService(InstructorDTO instructorDTO) {
    Instructor newInstructor = new Instructor();
    InstructorMapper.instructorToEntity(instructorDTO, newInstructor);

    String encryptedPassword = passwordEncoder.encode(instructorDTO.password());

    newInstructor.setPassword(encryptedPassword);

    if (instructorRepository.existsByEmail(newInstructor.getEmail())) {
      throw new EmailExistsException("The email '" + newInstructor.getEmail() + "' is already in use");
    }

    instructorRepository.save(newInstructor);

    return newInstructor;
  }

  public List<Instructor> findAllInstructors() {
    return instructorRepository.findAll();
  }

  public Optional<Instructor> findInstructorById(long id) {
    return instructorRepository.findById(id);
  }

  public Instructor updateInstructor(InstructorDTO dto, long id) {
    Instructor instructor = instructorRepository.findById(id).orElseThrow(() -> new NotFoundException("Instructor with the id '" + id + "' was not found"));
    InstructorMapper.instructorToEntity(dto, instructor);
    if (dto.courses() != null) {
      instructor.getCourses().clear();
      List<Course> courses = dto.courses().stream()
              .map(courseDTO -> CourseMapper.courseToEntity(courseDTO, instructor))
              .toList();
      instructor.getCourses().addAll(courses);
    }

    instructorRepository.save(instructor);

    return instructor;
  }

  public String addCourseToInstructor(long instructorId, long courseId) {
    Instructor instructor = instructorRepository.findById(instructorId)
            .orElseThrow(() -> new NotFoundException("Instructor with id '" + instructorId + "' not found"));

    Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new NotFoundException("Course with id '" + courseId + "' not found"));

    instructor.getCourses().add(course);
    course.setInstructor(instructor);

    instructorRepository.save(instructor);
    courseRepository.save(course);

    return "Course " + course.getTitle() + " successfully assigned to instructor: " + instructor.getName();

  }

  public void deleteInstructorById(long id) {
    instructorRepository.deleteById(id);
  }
}
