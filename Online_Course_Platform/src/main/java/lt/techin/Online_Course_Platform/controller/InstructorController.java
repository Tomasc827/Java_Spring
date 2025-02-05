package lt.techin.Online_Course_Platform.controller;


import jakarta.validation.Valid;
import lt.techin.Online_Course_Platform.dto.InstructorDTO;
import lt.techin.Online_Course_Platform.model.Instructor;
import lt.techin.Online_Course_Platform.service.InstructorService;
import lt.techin.Online_Course_Platform.utils.WebUtils;
import lt.techin.Online_Course_Platform.validation.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

  private InstructorService instructorService;

  @Autowired
  public InstructorController(InstructorService instructorService) {
    this.instructorService = instructorService;
  }

  @PostMapping
  public ResponseEntity<Instructor> addInstructor(@Valid @RequestBody InstructorDTO instructorDTO) {
    Instructor newInstructor = instructorService.postInstructorService(instructorDTO);

    URI location = WebUtils.createLocation("/{id}", newInstructor.getId());
    return ResponseEntity.created(location).body(newInstructor);
  }

  @PostMapping("{instructorId}/add-course/{courseId}")
  public ResponseEntity<String> addCourseToInstructor(@PathVariable long instructorId, @PathVariable long courseId) {
    String response = instructorService.addCourseToInstructor(instructorId, courseId);
    return ResponseEntity.ok().body(response);
  }


  @GetMapping
  public ResponseEntity<List<Instructor>> findAllInstructors() {
    List<Instructor> instructors = instructorService.findAllInstructors();
    return ResponseEntity.ok().body(instructors);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Instructor> findInstructorById(@PathVariable long id) {
    return instructorService.findInstructorById(id)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new NotFoundException("Instructor with the id '" + id + "' was not found"));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Instructor> updateInstructor(@Valid @RequestBody InstructorDTO instructorDTO, @PathVariable long id) {
    Instructor updatedInstructor = instructorService.updateInstructor(instructorDTO, id);

    URI location = WebUtils.createLocation("/{id}", updatedInstructor.getId());
    return ResponseEntity.created(location).body(updatedInstructor);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteInstructor(@PathVariable long id) {
    return instructorService.findInstructorById(id)
            .map(instructor -> {
              instructorService.deleteInstructorById(id);
              return ResponseEntity.noContent().build();
            }).orElseThrow(() -> new NotFoundException("Instructor with id '" + id + "' not found"));

  }

}
