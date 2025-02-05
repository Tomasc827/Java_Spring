package lt.techin.Dealership.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cars")
public class Car {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(length = 100, nullable = false)
  private String make;
  @Column(length = 100, nullable = false)
  private String model;
  @Column(length = 15, nullable = false)
  private EngineType engineType;
  @Column(length = 4, nullable = false)
  private int year;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "vin_id", referencedColumnName = "id")
  private VIN vin;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "car_feature",
          joinColumns = @JoinColumn(name = "car_id"),
          inverseJoinColumns = @JoinColumn(name = "feature_id")
  )
  @JsonIgnore
  private List<Feature> features;


  public Car() {

  }

  public List<Feature> getFeatures() {
    return features;
  }

  public void setFeatures(List<Feature> features) {
    this.features = features;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public EngineType getEngineType() {
    return engineType;
  }

  public void setEngineType(EngineType engineType) {
    this.engineType = engineType;
  }

  public long getId() {
    return id;
  }

  public VIN getVin() {
    return vin;
  }

  public void setVin(VIN vin) {
    this.vin = vin;
  }
}
