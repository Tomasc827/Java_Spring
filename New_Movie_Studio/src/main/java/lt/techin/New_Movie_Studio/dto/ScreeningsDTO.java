package lt.techin.New_Movie_Studio.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record ScreeningsDTO(String theaterName, LocalDate days, LocalTime time) {

}
