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
  public ResponseEntity<List<Movie>> getAllmovies() {
    List<Movie> movies = movieRepository.findAll();
    if (movies.isEmpty()) {
      return ResponseEntity.ok(movies);
    }
    return ResponseEntity.ok(movies);
  }

  @GetMapping("/search")
  public ResponseEntity<Movie> findMovie(@RequestParam String title) {
    return movieRepository.findByTitle(title).map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Movie> getMovie(@PathVariable long id) {
    return movieRepository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
    if (movie == null || movie.getDirector().isEmpty() || movie.getTitle().isEmpty()) {
      return ResponseEntity.status(400).build();
    }
    Movie savedMovie = movieRepository.save(movie);
    return ResponseEntity.status(201).body(savedMovie);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMovie(@PathVariable long id) {
    if (movieRepository.existsById(id)) {
      movieRepository.deleteById(id);
      return ResponseEntity.status(204).build();
    }
    return ResponseEntity.status(404).build();
  }

  @PatchMapping
  public ResponseEntity<Movie> patchMovie(@RequestBody Movie patchMovie, @PathVariable long id) {
    if (patchMovie == null) {
      return ResponseEntity.status(400).build();
    }
    return movieRepository.findById(id).map(movie -> {
      if (patchMovie.getTitle() != null && !patchMovie.getTitle().isEmpty()) {
        movie.setTitle(patchMovie.getTitle());
      }
      if (patchMovie.getDirector() != null && !patchMovie.getDirector().isEmpty()) {
        movie.setDirector(patchMovie.getDirector());
      }
      movieRepository.save(movie);
      return ResponseEntity.status(200).body(movie);
    }).orElseGet(() -> {
      return ResponseEntity.status(404).build();
    });
  }

  @PutMapping("/{id}")
  public ResponseEntity<Movie> replaceMovie(@RequestBody Movie newMovie, @PathVariable long id) {
    if (newMovie == null || newMovie.getTitle().isEmpty() || newMovie.getDirector().isEmpty()) {
      return ResponseEntity.status(400).build();
    }
    return movieRepository.findById(id)
            .map(movie -> {
              movie.setTitle(newMovie.getTitle());
              movie.setDirector(newMovie.getDirector());
              movieRepository.save(movie);
              return ResponseEntity.status(200).body(movie);
            })
            .orElseGet(() -> {
              return ResponseEntity.status(404).build();
            });
  }
}
