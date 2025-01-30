package lt.techin.Movie_Studio.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Movies")
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(columnDefinition = "VARCHAR(100)", nullable = false)
  private String title;
  @Column(length = 50, nullable = false)
  private String director;

  public Movie(String title, String director) {
    this.title = title;
    this.director = director;

  }

  public Movie() {

  }

  public long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDirector() {
    return director;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDirector(String director) {
    this.director = director;
  }
}
