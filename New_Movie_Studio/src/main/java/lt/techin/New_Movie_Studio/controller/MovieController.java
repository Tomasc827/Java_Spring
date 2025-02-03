package lt.techin.New_Movie_Studio.controller;


import jakarta.validation.Valid;
import lt.techin.New_Movie_Studio.Exception.MovieNotFoundException;
import lt.techin.New_Movie_Studio.dto.MovieDTO;
import lt.techin.New_Movie_Studio.model.Actor;
import lt.techin.New_Movie_Studio.model.Movie;
import lt.techin.New_Movie_Studio.model.Screening;
import lt.techin.New_Movie_Studio.service.MovieService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

  private MovieService movieService;

  @Autowired
  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping
  public ResponseEntity<List<Movie>> getAllMovies() {
    List<Movie> movies = movieService.getAllMovies();
    return ResponseEntity.ok().body(movies);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Movie> getMovieById(@PathVariable long id) {
    return movieService.getMovieById(id)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new MovieNotFoundException(id));

  }

  @PostMapping
  public ResponseEntity<Movie> postMovie(@Valid @RequestBody MovieDTO movieDTO) {
    Movie movie = new Movie();
    movie.setTitle(movieDTO.title());
    movie.setDirector((movieDTO.director()));
    movie.setScreenings(movie.getScreenings());

    movieService.saveMovie(movie);

    URI location = URI.create("/books/" + movie.getId());
    return ResponseEntity.created(location).body(movie);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Movie> replaceMovie(@Valid @RequestBody MovieDTO newMovieDTO, @PathVariable long id) {
    return movieService.getMovieById(id).map(movie -> {
      movie.setDirector(newMovieDTO.director());
      movie.setTitle(newMovieDTO.title());

      if (newMovieDTO.screenings() != null && !newMovieDTO.screenings().isEmpty()) {

        List<Screening> updatedScreenings = newMovieDTO.screenings().stream()
                .map(screeningDTO -> {
                  Screening screening = new Screening();
                  screening.setTheaterName(screeningDTO.theaterName());
                  screening.setDays(screeningDTO.days());
                  screening.setTime(screeningDTO.time());
                  screening.setMovie(movie);
                  return screening;
                })
                .collect(Collectors.toList());

        movie.setScreenings(updatedScreenings);
      }
      if (newMovieDTO.actors() != null && !newMovieDTO.actors().isEmpty()) {
        List<Actor> updatedActors = newMovieDTO.actors().stream().map(actorDTO -> {
          Actor actor = new Actor();
          actor.setName(actorDTO.name());
          actor.setDob(actorDTO.dob());
          return actor;
        }).collect(Collectors.toList());
        movie.setActors(updatedActors);
      }

      movieService.saveMovie(movie);

      URI location = URI.create("/movies/" + movie.getId());
      return ResponseEntity.created(location).body(movie);
    }).orElseThrow(() -> new MovieNotFoundException(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteMovie(@PathVariable long id) {
    return movieService.getMovieById(id).map(movie -> {
      movieService.deleteMovieById(id);
      return ResponseEntity.noContent().build();
    }).orElseThrow(() -> new MovieNotFoundException(id));
  }

}
