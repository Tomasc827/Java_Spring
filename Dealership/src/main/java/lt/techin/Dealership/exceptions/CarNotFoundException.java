package lt.techin.Dealership;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(long id) {
        super("Car with the id '" + id + "' does not exist");
    }
}
