package lt.techin.Dealership.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record VINDTO (@NotBlank(message = "Vin cannot be blank")
                      @Size(min = 17,max = 17,message = "Vin is exactly 17 chars")
                      String code){
}
