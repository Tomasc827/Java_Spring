package lt.techin.media_site.controller;


import jakarta.validation.Valid;
import lt.techin.media_site.dto.user.UserMapper;
import lt.techin.media_site.dto.user.UserPostDTO;
import lt.techin.media_site.dto.user.UserResponseDTO;
import lt.techin.media_site.model.User;
import lt.techin.media_site.service.UserService;
import lt.techin.media_site.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserPostDTO dto) {
        User user = userService.createUser(dto);

        UserResponseDTO response = UserMapper.toResponseDTO(user);
        URI location = WebUtils.createLocation("/{id}",user.getId());

        return ResponseEntity.created(location).body(response);
    }
//    @GetMapping
//    public ResponseEntity<List<User>> findAllUsers() {
//        List<User> users = userService.findAllUsers();
//        return ResponseEntity.ok(users);
//    }

}
