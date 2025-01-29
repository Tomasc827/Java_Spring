package lt.techin.h2_attempt.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@RestController
public class TargetController {

  @GetMapping(value = "/target", produces = MediaType.IMAGE_JPEG_VALUE)
  public ResponseEntity<byte[]> getMoe() throws IOException {
    var moeImage = new ClassPathResource("/images/moe.jpg");
    byte[] imageBytes = StreamUtils.copyToByteArray(moeImage.getInputStream());
    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
  }

}
