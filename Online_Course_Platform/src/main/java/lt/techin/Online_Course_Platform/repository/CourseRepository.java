package lt.techin.Online_Course_Platform.repository;

import lt.techin.Online_Course_Platform.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {


}
