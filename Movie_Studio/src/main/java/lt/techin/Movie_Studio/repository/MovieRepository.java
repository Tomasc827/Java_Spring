package lt.techin.Movie_Studio.repository;

import lt.techin.Movie_Studio.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
