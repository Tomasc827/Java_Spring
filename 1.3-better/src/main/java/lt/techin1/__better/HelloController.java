package lt.techin1.__better;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HelloController {
    @GetMapping("/")
    public String index() {
        return "Nothing here but geese!";
    }
    @GetMapping(value = "/geese",produces = MediaType.IMAGE_GIF_VALUE)
    public ResponseEntity<byte[]> getImage() throws IOException {
        var imgFile = new ClassPathResource("/images/geese.gif");
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_GIF).body(bytes);
    }
}
