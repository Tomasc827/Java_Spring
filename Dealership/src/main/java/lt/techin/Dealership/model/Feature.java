package lt.techin.Dealership.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "features")
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,length = 100)
    private String carFeature;

    @Column(nullable = false,length = 500)
    private String description;

    @ManyToMany(mappedBy = "features")
    private List<Car> cars;


    public Feature() {

    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public long getId() {
        return id;
    }

    public String getCarFeature() {
        return carFeature;
    }

    public void setCarFeature(String carFeature) {
        this.carFeature = carFeature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
