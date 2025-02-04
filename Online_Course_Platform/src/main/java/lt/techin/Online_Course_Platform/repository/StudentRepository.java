package lt.techin.Online_Course_Platform.repository;

import lt.techin.Online_Course_Platform.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
    boolean existsByEmail(String email);
}
