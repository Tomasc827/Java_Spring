package lt.techin.New_Movie_Studio.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "screenings")
public class Screening {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(length = 100, nullable = false)
  private String theaterName;
  @Column(nullable = false)
  private LocalDate days;
  @Column(nullable = false)
  private LocalTime time;

  @ManyToOne
  @JoinColumn(name = "movie_id")
  @JsonIgnore
  private Movie movie;


  public Screening() {

  }

  public long getId() {
    return id;
  }


  public String getTheaterName() {
    return theaterName;
  }

  public void setTheaterName(String theaterName) {
    this.theaterName = theaterName;
  }

  public LocalDate getDays() {
    return days;
  }

  public void setDays(LocalDate days) {
    this.days = days;
  }

  public LocalTime getTime() {
    return time;
  }

  public void setTime(LocalTime time) {
    this.time = time;
  }

  public Movie getMovie() {
    return movie;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }
}
