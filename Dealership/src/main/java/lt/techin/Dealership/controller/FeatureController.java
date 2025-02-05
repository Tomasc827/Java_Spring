package lt.techin.Dealership.controller;


import jakarta.validation.Valid;
import lt.techin.Dealership.WebUtils;
import lt.techin.Dealership.dto.FeatureDTO;
import lt.techin.Dealership.exceptions.FeatureNotFoundException;
import lt.techin.Dealership.model.Feature;
import lt.techin.Dealership.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/features")
public class FeatureController {

  private FeatureService featureService;

  @Autowired
  public FeatureController(FeatureService featureService) {
    this.featureService = featureService;
  }


  @PostMapping
  public ResponseEntity<Feature> addFeature(@Valid @RequestBody FeatureDTO featureDTO) {
    Feature feature = featureService.postFeatureService(featureDTO);

    URI location = WebUtils.createLocation("/{id}", feature.getId());
    return ResponseEntity.created(location).body(feature);
  }

  @GetMapping
  public ResponseEntity<List<Feature>> findAllFeatures() {
    List<Feature> features = featureService.findAllFeatures();
    return ResponseEntity.ok(features);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Feature> findFeatureById(@PathVariable long id) {
    return featureService.findFeatureById(id)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new FeatureNotFoundException(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Feature> replaceFeature(@Valid @RequestBody FeatureDTO featureDTO, @PathVariable long id) {
    Feature updateFeature = featureService.updateFeature(featureDTO, id);

    URI location = WebUtils.createLocation("/{id}", updateFeature.getId());
    return ResponseEntity.created(location).body(updateFeature);


  }


}
