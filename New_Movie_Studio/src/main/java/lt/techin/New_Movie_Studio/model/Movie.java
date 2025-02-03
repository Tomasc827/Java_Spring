package lt.techin.New_Movie_Studio.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, length = 120)
  private String title;

  @Column(nullable = false, length = 120)
  private String director;

  @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
  private List<Screening> screenings;

  @ManyToMany
  @JoinTable(
          name = "movie_actor",
          joinColumns = @JoinColumn(name = "movie_id"),
          inverseJoinColumns = @JoinColumn(name = "actor_id")
  )
  private List<Actor> actors;

  public Movie(String title, String director) {
    this.title = title;
    this.director = director;
  }

  public Movie() {

  }

  public List<Screening> getScreenings() {
    return screenings;
  }

  public void setScreenings(List<Screening> screenings) {
    this.screenings = screenings;
  }

  public long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDirector() {
    return director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public List<Actor> getActors() {
    return actors;
  }

  public void setActors(List<Actor> actors) {
    this.actors = actors;
  }
}
