package lt.techin.cat_cafe_shop.controller;

import jakarta.validation.Valid;
import lt.techin.cat_cafe_shop.dto.adoption.CatAdoptionPutRequestDTO;
import lt.techin.cat_cafe_shop.dto.adoption.CatAdoptionPutResponseDTO;
import lt.techin.cat_cafe_shop.dto.adoption.CatAdoptionRequestDTO;
import lt.techin.cat_cafe_shop.dto.adoption.CatAdoptionResponseDTO;
import lt.techin.cat_cafe_shop.model.CatAdoption;
import lt.techin.cat_cafe_shop.service.CatAdoptionService;
import lt.techin.cat_cafe_shop.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/adoptions")
public class CatAdoptionController {

    private final CatAdoptionService catAdoptionService;

    @Autowired
    public CatAdoptionController(CatAdoptionService catAdoptionService) {
        this.catAdoptionService = catAdoptionService;
    }

    @PostMapping("/apply")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    public ResponseEntity<CatAdoptionResponseDTO> createAdoption(@Valid @RequestBody CatAdoptionRequestDTO dto) {
        CatAdoption catAdoption = catAdoptionService.createAdoption(dto);
        CatAdoptionResponseDTO responseDTO = new CatAdoptionResponseDTO(catAdoption.getId(),
                catAdoption.getUser(),
                catAdoption.getCatName(),
                catAdoption.getStatus(),
                catAdoption.getApplicationDate());
        URI location = WebUtils.createLocation("/{id}",catAdoption.getId());
        return ResponseEntity.created(location).body(responseDTO);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    public ResponseEntity<List<CatAdoption>> findAllUserCatAdoptions() {
        return ResponseEntity.ok(catAdoptionService.findAllUserCatAdoptions());
    }
    @GetMapping("/pending")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<List<CatAdoption>> findAllPendingCatAdoptions() {
        return ResponseEntity.ok(catAdoptionService.findAllPendingCatAdoption());
    }

    @PutMapping("/{adoptionId}/approve")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<?> approveCatAdoption(@Valid @RequestBody CatAdoptionPutRequestDTO dto
            , @PathVariable long adoptionId) {
        CatAdoption catAdoption = catAdoptionService.changeAdoptionStatus(dto,adoptionId);
        CatAdoptionPutResponseDTO responseDTO = new CatAdoptionPutResponseDTO(catAdoption.getId(),
                catAdoption.getCatName(),
                catAdoption.getStatus());
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/approved")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<List<CatAdoption>> findAllApprovedAdoptions() {
        return ResponseEntity.ok().body(catAdoptionService.findAllApprovedCatAdoptions());
    }



}
