package lt.techin.media_site.service;


import lt.techin.media_site.dto.media.MediaFrontDTO;
import lt.techin.media_site.dto.media.MediaMapper;
import lt.techin.media_site.dto.media.MediaPostDTO;
import lt.techin.media_site.model.Category;
import lt.techin.media_site.model.Media;
import lt.techin.media_site.model.media_enum.CategoryEnum;
import lt.techin.media_site.repository.CategoryRepository;
import lt.techin.media_site.repository.MediaRepository;
import lt.techin.media_site.validation.exception.IdDoesNotExistException;
import lt.techin.media_site.validation.exception.MediaExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService {

    private MediaRepository mediaRepository;
    private CategoryRepository categoryRepository;
    @Autowired
    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public Media addMedia(MediaPostDTO dto) {
        Media media = MediaMapper.toEntity(dto);
        if (mediaRepository.existsByDescription(media.getDescription()) && mediaRepository.existsByTitle(dto.title())) {
            throw new MediaExistsException("Media with the title '" + media.getTitle() + "' and description already exists");
        }
        if (media.getImageURL() == null) {
            media.setImageURL("https://static.printler.com/cache/f/2/f/e/5/e/f2fe5e97da682d7c58523ae2d10d9114167ac89b.jpg");
        }

        mediaRepository.save(media);
        return media;
    }

    public List<Media> findAllMedia() {
        return mediaRepository.findAll();
    }
    public List<MediaFrontDTO> findAllDTOMedia() {
        return MediaMapper.getToDTO(findAllMedia());
    }

    public Media addCategory(long mediaId, CategoryEnum categoryEnum) {
        Media media =
                mediaRepository.findById(mediaId).orElseThrow(() -> new IdDoesNotExistException("Media with id '" + mediaId +
                        "does not exist"));
        if(categoryEnum.toString().equals(media.getCategories().stream().map(Category::getCategory))) {
            throw new IllegalArgumentException("TBD");
        }
        Category category =
                categoryRepository.findByCategory(categoryEnum).orElseThrow(() -> new IdDoesNotExistException("Category with " +
                        "id '" + categoryEnum + "'does not exist"));

        media.getCategories().add(category);
        return mediaRepository.save(media);
    }

}
