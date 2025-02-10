package lt.techin.media_site.controller;


import jakarta.validation.Valid;
import lt.techin.media_site.dto.media.*;
import lt.techin.media_site.model.Media;
import lt.techin.media_site.model.media_enum.CategoryEnum;
import lt.techin.media_site.service.CategoryService;
import lt.techin.media_site.service.MediaService;
import lt.techin.media_site.util.WebUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/medias")
public class MediaController {

    private MediaService mediaService;
    private CategoryService categoryService;

    public MediaController(MediaService mediaService,CategoryService categoryService) {
        this.mediaService = mediaService;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<MediaResponseDTO> addMedia(@Valid @RequestBody MediaPostDTO dto) {
        Media media = mediaService.addMedia(dto);
        MediaResponseDTO responseDTO = MediaMapper.toResponseDTO(media);

        URI location = WebUtils.createLocation("/{id}",media.getId());

        return ResponseEntity.created(location).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllMedia() {
        List<Media> medias = mediaService.findAllMedia();
        List<MediaFrontDTO> dtos = MediaMapper.getToDTO(medias);
        return ResponseEntity.ok(dtos);
    }
    @PostMapping("/{mediaId}/add-category")
    public ResponseEntity<?> addCategory(@PathVariable long mediaId,@Valid @RequestBody CategoryEnum categoryEnum) {
        mediaService.addCategory(mediaId,categoryEnum);
        String category = categoryService.findCategoryByCategory(categoryEnum).getCategory().toString();
        return ResponseEntity.ok().body(category);
    }

}
