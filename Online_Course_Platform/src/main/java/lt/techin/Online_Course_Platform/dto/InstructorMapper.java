package lt.techin.Online_Course_Platform.dto;


import lt.techin.Online_Course_Platform.model.Instructor;
import lt.techin.Online_Course_Platform.service.InstructorService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;


public class InstructorMapper {


  public static Instructor instructorToEntity(InstructorDTO instructorDTO, Instructor instructor) {
    instructor.setAddress(instructorDTO.address());
    instructor.setEmail(instructorDTO.email());
    instructor.setExpertise(instructorDTO.expertise());
    instructor.setName(instructorDTO.name());

    return instructor;
  }

  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }


//  public static InstructorDTO instructorToDTO(Instructor instructor) {
//    List<CourseDTO> coursesDTOs = instructor.getCourses().stream()
//            .map(CourseMapper::courseToDto)
//            .toList();
//    return new InstructorDTO(instructor.getName(),
//            instructor.getEmail(),
//            instructor.getExpertise(),
//            instructor.getAddress(),
//            coursesDTOs);
//
//  }


}
