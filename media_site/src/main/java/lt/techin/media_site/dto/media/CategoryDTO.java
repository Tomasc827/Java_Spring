package lt.techin.media_site.dto.media;

import jakarta.validation.constraints.NotNull;
import lt.techin.media_site.model.Media;
import lt.techin.media_site.model.media_enum.CategoryEnum;

import java.util.List;

public record CategoryDTO(@NotNull(message = "Category cannot be null")
                          CategoryEnum categoryEnum,
                          @NotNull(message = "Categories list cannot be null")
                          List<Media> medias) {
}
