package lt.techin.media_site.dto.media;

import lt.techin.media_site.model.Category;
import lt.techin.media_site.model.media_enum.AgeRating;
import lt.techin.media_site.model.media_enum.Type;

import java.time.LocalDate;
import java.util.List;


public record MediaFrontDTO(String title,
                            LocalDate releaseDate,
                            String videoURL,
                            String imageURL,
                            Type type,
                            AgeRating ageRating,
                            String description,
                            int episodeCount,
                            int episodeTotal,
                            List<Category> categories) {
}
