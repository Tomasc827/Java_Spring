package lt.techin.New_Movie_Studio.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "actors")
public class Actor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(length = 100, nullable = false)
  private String name;
  @Column(length = 12, nullable = false)
  private LocalDate dob;

  @ManyToMany(mappedBy = "actors")
  private List<Movie> movies;

  public Actor() {
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getDob() {
    return dob;
  }

  public void setDob(LocalDate dob) {
    this.dob = dob;
  }

  public List<Movie> getMovies() {
    return movies;
  }

  public void setMovies(List<Movie> movies) {
    this.movies = movies;
  }
}
