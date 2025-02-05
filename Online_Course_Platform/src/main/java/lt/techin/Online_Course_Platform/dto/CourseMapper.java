package lt.techin.Online_Course_Platform.dto;

import lt.techin.Online_Course_Platform.model.Course;
import lt.techin.Online_Course_Platform.model.Instructor;


public class CourseMapper {


  public static CourseDTO courseToDto(Course course) {
    return new CourseDTO(
            course.getTitle(),
            course.getDescription(),
            course.getDuration(),
            course.getInstructor()
    );
  }

  public static Course courseToEntity(CourseDTO courseDTO, Instructor instructor) {
    Course course = new Course();
    course.setInstructor(instructor);
    course.setDescription(courseDTO.description());
    course.setDuration(courseDTO.duration());
    course.setTitle(courseDTO.title());
    return course;
  }

  public static Course courseToEntity(CourseDTO courseDTO) {
    Course course = new Course();
    course.setDescription(courseDTO.description());
    course.setDuration(courseDTO.duration());
    course.setTitle(courseDTO.title());
    return course;
  }

}
