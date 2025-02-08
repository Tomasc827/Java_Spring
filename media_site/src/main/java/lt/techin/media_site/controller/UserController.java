package lt.techin.media_site.controller;


import jakarta.validation.Valid;
import lt.techin.media_site.dto.user.*;
import lt.techin.media_site.model.User;
import lt.techin.media_site.repository.UserRepository;
import lt.techin.media_site.service.UserService;
import lt.techin.media_site.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserPostDTO dto) {
        User user = userService.createUser(dto);

        UserResponseDTO response = UserMapper.toResponseDTO(user);
        URI location = WebUtils.createLocation("/{id}",user.getId());

        return ResponseEntity.created(location).body(response);
    }
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> userDeleteSelf(@PathVariable long id) {
        userService.userDeleteSelf(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> userPatch(@Valid @RequestBody UserPatchDTO dto, @PathVariable long id) {
        User user = userService.patchUserById(dto,id);
        UserResponseDTO userResponseDTO = UserMapper.toResponseDTO(user);
        return ResponseEntity.ok().body(userResponseDTO);
    }
    @GetMapping
    public ResponseEntity<List<?>> findAllUsers() {
        List<User> users = userService.findAllUsers();
        List<UserGetDTO> dtoList = UserMapper.getToEntity(users);
        return ResponseEntity.ok(dtoList);
    }

}
