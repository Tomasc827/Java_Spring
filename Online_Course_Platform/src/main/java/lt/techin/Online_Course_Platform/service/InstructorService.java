package lt.techin.Online_Course_Platform.service;


import lt.techin.Online_Course_Platform.dto.InstructorDTO;
import lt.techin.Online_Course_Platform.model.Course;
import lt.techin.Online_Course_Platform.model.Instructor;
import lt.techin.Online_Course_Platform.repository.CourseRepository;
import lt.techin.Online_Course_Platform.repository.InstructorRepository;
import lt.techin.Online_Course_Platform.validation.EmailExistsException;
import lt.techin.Online_Course_Platform.validation.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

  private InstructorRepository instructorRepository;
  private CourseRepository courseRepository;

  @Autowired
  public InstructorService(InstructorRepository instructorRepository, CourseRepository courseRepository) {
    this.instructorRepository = instructorRepository;
    this.courseRepository = courseRepository;
  }

  public Instructor postInstructorService(InstructorDTO instructorDTO) {
    Instructor newInstructor = new Instructor();
    newInstructor.setAddress(instructorDTO.address());
    newInstructor.setEmail(instructorDTO.email());
    newInstructor.setExpertise(instructorDTO.expertise());
    newInstructor.setName(instructorDTO.name());
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
    instructor.setName(dto.name());
    instructor.setEmail(dto.email());
    instructor.setExpertise(dto.expertise());
    instructor.setAddress(dto.address());
    if (dto.courses() !=null) {
      instructor.setCourses(dto.courses().stream().map(courseDTO -> {
        Course course = new Course();
        course.setInstructor(courseDTO.instructor());
        course.setDescription(courseDTO.description());
        course.setDuration(courseDTO.duration());
        course.setTitle(courseDTO.title());
        instructor.getCourses().add(course);
        return course;
      }).toList());
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
