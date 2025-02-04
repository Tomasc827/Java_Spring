package lt.techin.Online_Course_Platform.controller;


import jakarta.validation.Valid;
import lt.techin.Online_Course_Platform.dto.StudentDTO;
import lt.techin.Online_Course_Platform.model.Student;
import lt.techin.Online_Course_Platform.service.StudentService;
import lt.techin.Online_Course_Platform.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAllStudents() {
        List<Student> students = studentService.findAllStudents();
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@Valid @RequestBody StudentDTO dto) {
        Student student = studentService.addStudent(dto);
        URI location = WebUtils.createLocation("/{id}", student.getId());
        return ResponseEntity.created(location).body(student);
    }

    @PostMapping("/{studentId}/enroll-course/{courseId}")
    public ResponseEntity<String> enrollStudent(@PathVariable long studentId,@PathVariable long courseId) {
      String response = studentService.addCourseToStudent(studentId,courseId);
        return ResponseEntity.ok().body(response);
    }

}
