package com.example22.controller;


import com.example22.model.Movie;
import com.example22.repository.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
  private MovieRepository movieRepository;

  public MovieController(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  @GetMapping
  public List<Movie> getAllmovies() {
    return movieRepository.findAll();
  }

  @GetMapping("/{id}")
  public Movie getMovie(@PathVariable long id) {
    return movieRepository.findById(id).orElseThrow(IllegalArgumentException::new);
  }

  @PostMapping
  public Movie createMovie(@RequestBody Movie movie) {
    return movieRepository.save(movie);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Movie> deleteMovie(@PathVariable long id) {
    Movie movie = movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
    movieRepository.deleteById(id);
    return ResponseEntity.ok(movie);
  }
}
