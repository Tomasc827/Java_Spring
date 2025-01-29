package lt.techin21.controllers;


import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

//@RestController
//public class GooseController {
//  @GetMapping(value = "/goose", produces = MediaType.IMAGE_JPEG_VALUE)
//  public ResponseEntity<byte[]> getGoose() throws IOException {
//    var gooseImage = new ClassPathResource("/image/goose.jpg");
//    byte[] imageBytes = StreamUtils.copyToByteArray(gooseImage.getInputStream());
//    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
//  }

//}
