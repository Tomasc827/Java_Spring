package lt.techin.Online_Course_Platform.controller;


import jakarta.validation.Valid;
import lt.techin.Online_Course_Platform.dto.CourseDTO;
import lt.techin.Online_Course_Platform.model.Course;
import lt.techin.Online_Course_Platform.service.CourseService;
import lt.techin.Online_Course_Platform.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

  private CourseService courseService;

  @Autowired
  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @PostMapping
  public ResponseEntity<Course> addCourse(@Valid @RequestBody CourseDTO courseDTO) {
    Course course = courseService.addCourse(courseDTO);
    URI location = WebUtils.createLocation("/{id}", course.getId());
    return ResponseEntity.created(location).body(course);
  }

  @GetMapping
  public ResponseEntity<List<Course>> findAllCourses() {
    List<Course> courses = courseService.findAllCourses();
    return ResponseEntity.ok(courses);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Course> updatedCourse(@Valid @RequestBody CourseDTO courseDTO, @PathVariable long id) {
    Course updatedCourse = courseService.updateCourse(courseDTO, id);

    URI location = WebUtils.createLocation("/{id}", updatedCourse.getId());
    return ResponseEntity.created(location).body(updatedCourse);

  }

}
