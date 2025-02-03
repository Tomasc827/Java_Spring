package lt.techin.New_Movie_Studio.repository;

import lt.techin.New_Movie_Studio.model.Actor;
import lt.techin.New_Movie_Studio.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
  @Query("SELECT m FROM Movie m LEFT JOIN FETCH m.actors")
  List<Movie> findAllWithActors();
}
