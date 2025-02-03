package lt.techin.New_Movie_Studio.Exception;

public class MovieNotFoundException extends RuntimeException {
  public MovieNotFoundException(long id) {
    super("The movie with id '" + id + "' cannot be found");
  }
}
