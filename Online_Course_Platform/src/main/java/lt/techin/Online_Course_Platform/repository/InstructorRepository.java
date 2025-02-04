package lt.techin.Online_Course_Platform.repository;

import lt.techin.Online_Course_Platform.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
  boolean existsByEmail(String email);


}
