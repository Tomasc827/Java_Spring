package lt.techin.New_Movie_Studio.service;


import lt.techin.New_Movie_Studio.model.Movie;
import lt.techin.New_Movie_Studio.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

  private MovieRepository movieRepository;

  @Autowired
  public MovieService(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }


  public List<Movie> getAllMovies() {
    return movieRepository.findAll();
  }

  public Optional<Movie> getMovieById(long id) {
    return movieRepository.findById(id);
  }

  public void saveMovie(Movie movie) {
    movieRepository.save(movie);
  }

  public void deleteMovieById(long id) {
    movieRepository.deleteById(id);
  }
}
