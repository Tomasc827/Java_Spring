package lt.techin.cat_cafe_shop.dto.adoption;

import lt.techin.cat_cafe_shop.model.AdoptionStatus;

public record CatAdoptionPutResponseDTO (long id, String catName, AdoptionStatus status){
}
