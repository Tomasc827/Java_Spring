package lt.techin.Dealership.exceptions;

public class FeatureNotFoundException extends RuntimeException {
  public FeatureNotFoundException(long id) {
    super("Feature with id '" + id + "' does not exist");
  }
}
