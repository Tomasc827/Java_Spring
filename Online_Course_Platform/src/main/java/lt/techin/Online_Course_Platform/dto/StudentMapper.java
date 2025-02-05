package lt.techin.Online_Course_Platform.dto;

import lt.techin.Online_Course_Platform.model.Student;

public class StudentMapper {


  public static Student toDTO(StudentDTO dto) {
    Student student = new Student();
    student.setName(dto.name());
    student.setDob(dto.dob());
    student.setEmail(dto.email());

    return student;
  }

  public static Student toDTO(StudentDTO dto, Student student) {
    student.setName(dto.name());
    student.setDob(dto.dob());
    student.setEmail(dto.email());
    if (dto.courses() != null) {
      student.setCourses(dto.courses());
    }
    return student;
  }

}
