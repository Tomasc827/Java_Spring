package lt.techin.media_site.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/admin")
    public ResponseEntity<?> adminAccessCheck() {
        return ResponseEntity.ok("Gigachad");
    }
}
