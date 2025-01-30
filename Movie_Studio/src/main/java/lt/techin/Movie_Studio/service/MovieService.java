package lt.techin.Movie_Studio.service;

import lt.techin.Movie_Studio.model.Movie;
import lt.techin.Movie_Studio.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MovieService {

  private final MovieRepository movieRepository;

  @Autowired
  public MovieService(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  public List<Movie> findAllMovies() {
    return movieRepository.findAll();
  }


  public void saveMovie(Movie movie) {
    movieRepository.save(movie);
  }

  public boolean existsByID(long id) {
    return movieRepository.existsById(id);
  }

  public Optional<Movie> findMovieById(long id) {
    return movieRepository.findById(id);
  }


  public void deleteMovieById(long id) {
    movieRepository.deleteById(id);
  }
}
