package lt.techin.media_site.dto.media;

import lt.techin.media_site.model.Media;
import lt.techin.media_site.model.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MediaMapper {



    public static Media toEntity(MediaPostDTO dto) {
        Media media = new Media();
        media.setDescription(dto.description());
        media.setAgeRating(dto.ageRating());
        media.setEpisodeCount(dto.episodeCount());
        media.setEpisodeTotal(dto.episodeTotal());
        media.setImageURL(dto.imageURL());
        media.setType(dto.type());
        media.setTitle(dto.title());
        media.setReleaseDate(dto.releaseDate());
        media.setVideoURL(dto.videoURL());
        return media;
    }
    public static List<MediaFrontDTO> getToDTO(List<Media> media) {
        return media.stream().map(mediaOne ->
                {
                    return new MediaFrontDTO(
                            mediaOne.getTitle(),
                            mediaOne.getReleaseDate(),
                            mediaOne.getVideoURL(),
                            mediaOne.getImageURL(),
                            mediaOne.getType(),
                            mediaOne.getAgeRating(),
                            mediaOne.getDescription(),
                            mediaOne.getEpisodeCount(),
                            mediaOne.getEpisodeTotal(),
                            mediaOne.getCategories()
                    );
                }
        ).toList();
    }

    public static MediaResponseDTO toResponseDTO(Media media) {
        return new MediaResponseDTO(media.getId(),
                media.getTitle(),
                media.getReleaseDate());
    }

}
