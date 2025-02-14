package lt.techin.cat_cafe_shop.service;


import lt.techin.cat_cafe_shop.dto.adoption.CatAdoptionPutRequestDTO;
import lt.techin.cat_cafe_shop.dto.adoption.CatAdoptionRequestDTO;
import lt.techin.cat_cafe_shop.model.AdoptionStatus;
import lt.techin.cat_cafe_shop.model.CatAdoption;
import lt.techin.cat_cafe_shop.model.User;
import lt.techin.cat_cafe_shop.repository.CatAdoptionsRepository;
import lt.techin.cat_cafe_shop.repository.UserRepository;
import lt.techin.cat_cafe_shop.validation.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CatAdoptionService {

    private final CatAdoptionsRepository catAdoptionsRepository;
    private final UserRepository userRepository;

    public CatAdoptionService(CatAdoptionsRepository catAdoptionsRepository, UserRepository userRepository) {
        this.catAdoptionsRepository = catAdoptionsRepository;
        this.userRepository = userRepository;
    }

    public CatAdoption createAdoption(CatAdoptionRequestDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new NotFoundException("User not found"));

        CatAdoption catAdoption = new CatAdoption();

        catAdoption.setCatName(dto.catName());
        catAdoption.setStatus(AdoptionStatus.PENDING);
        catAdoption.setApplicationDate(LocalDateTime.now());
        catAdoption.setUser(user);
        user.getCatAdoptions().add(catAdoption);
        catAdoptionsRepository.save(catAdoption);
        return catAdoption;
    }

    public List<CatAdoption> findAllUserCatAdoptions(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new NotFoundException("User not found"));
        return catAdoptionsRepository.findAllByUser(user);
    }

    public List<CatAdoption> findAllPendingCatAdoption() {
        return catAdoptionsRepository.findAllByStatus(AdoptionStatus.PENDING);
    }

    public CatAdoption changeAdoptionStatus(CatAdoptionPutRequestDTO dto, long id) {
        CatAdoption catAdoption = catAdoptionsRepository.findById(id).orElseThrow(() -> new NotFoundException("Adoption with id '" + id + "was not found"));
        catAdoption.setStatus(dto.status());
        catAdoptionsRepository.save(catAdoption);
        return catAdoption;
    }
    // new ResponseEntity<>(Map.of("error", "Adoption request not found"), HttpStatus.NOT_FOUND)
    public List<CatAdoption> findAllApprovedCatAdoptions() {
        return catAdoptionsRepository.findAllByStatus(AdoptionStatus.APPROVED);
    }


}
