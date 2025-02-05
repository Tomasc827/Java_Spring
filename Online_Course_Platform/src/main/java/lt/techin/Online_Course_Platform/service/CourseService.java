package lt.techin.Online_Course_Platform.service;


import lt.techin.Online_Course_Platform.dto.CourseDTO;
import lt.techin.Online_Course_Platform.dto.CourseMapper;
import lt.techin.Online_Course_Platform.model.Course;
import lt.techin.Online_Course_Platform.model.Instructor;
import lt.techin.Online_Course_Platform.repository.CourseRepository;
import lt.techin.Online_Course_Platform.repository.InstructorRepository;
import lt.techin.Online_Course_Platform.validation.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

  private CourseRepository courseRepository;
  private InstructorRepository instructorRepository;

  @Autowired
  public CourseService(CourseRepository courseInterface, InstructorRepository instructorRepository) {
    this.courseRepository = courseInterface;
    this.instructorRepository = instructorRepository;
  }


  public Course addCourse(CourseDTO courseDTO) {
    Course course = CourseMapper.courseToEntity(courseDTO);
    courseRepository.save(course);
    return course;

  }

  public Course updateCourse(CourseDTO courseDTO, long id) {
    Course existingCourse = courseRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Course with id '" + id + "does not exist"));
    existingCourse.setTitle(courseDTO.title());
    existingCourse.setDuration(courseDTO.duration());
    existingCourse.setDescription(courseDTO.description());
    if (courseDTO.instructor() != null) {
      Instructor newInstructor = courseDTO.instructor();

      if (existingCourse.getInstructor() == null) {
        existingCourse.setInstructor(newInstructor);
      }
    }

    courseRepository.save(existingCourse);
    return existingCourse;
  }

  public List<Course> findAllCourses() {
    return courseRepository.findAll();
  }
}
