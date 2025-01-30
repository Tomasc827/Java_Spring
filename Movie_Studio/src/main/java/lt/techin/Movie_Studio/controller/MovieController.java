package lt.techin.Movie_Studio.controller;


import jakarta.validation.Valid;
import lt.techin.Movie_Studio.records.MovieDTO;
import lt.techin.Movie_Studio.model.Movie;
import lt.techin.Movie_Studio.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
  private final MovieService movieService;

  @Autowired
  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping
  public ResponseEntity<List<Movie>> getAllMovies() {
    List<Movie> movies = movieService.findAllMovies();
    return ResponseEntity.ok(movies);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Movie> getMovieById(@PathVariable long id) {
    Optional<Movie> movie = movieService.findMovieById(id);
    if (movie.isEmpty()) {
      return ResponseEntity.status(404).build();
    }
    return ResponseEntity.status(200).body(movie.get());
  }

  @PostMapping
  public ResponseEntity<?> addMovie(@Valid @RequestBody MovieDTO movieDTO) {
    Movie movie = new Movie();
    movie.setTitle(movieDTO.title());
    movie.setDirector(movieDTO.director());

    movieService.saveMovie(movie);
    return ResponseEntity.status(201).body(movie);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> replaceMovie(@Valid @RequestBody MovieDTO newMovieDTO, @PathVariable long id) {
    return movieService.findMovieById(id).map(movie -> {

      movie.setTitle(newMovieDTO.title());
      movie.setDirector(newMovieDTO.director());

      movieService.saveMovie(movie);

      return ResponseEntity.status(201).body(movie);

    }).orElseGet(() -> ResponseEntity.status(404).build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Movie> deleteMovie(@PathVariable long id) {
    Optional<Movie> movie = movieService.findMovieById(id);
    if (movie.isEmpty()) {
      return ResponseEntity.status(404).build();
    }
    movieService.deleteMovieById(id);
    return ResponseEntity.status(204).body(movie.get());
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Movie> patchMovie(@Valid @RequestBody MovieDTO patchMovieDTO, @PathVariable long id) {
    return movieService.findMovieById(id).map(movie -> {
      if (patchMovieDTO.director() != null && !patchMovieDTO.director().isEmpty()) {
        movie.setDirector(patchMovieDTO.director());
      }
      if (patchMovieDTO.title() != null && !patchMovieDTO.title().isEmpty()) {
        movie.setTitle(patchMovieDTO.title());
      }
      movieService.saveMovie(movie);
      return ResponseEntity.status(200).body(movie);
    }).orElseGet(() -> {
      return ResponseEntity.status(404).build();
    });
  }

}
