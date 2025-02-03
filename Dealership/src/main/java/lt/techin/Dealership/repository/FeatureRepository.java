package lt.techin.Dealership.repository;

import lt.techin.Dealership.dto.FeatureDTO;
import lt.techin.Dealership.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature,Long> {
}
