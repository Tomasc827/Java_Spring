package lt.techin.jwt.controller;

import jakarta.validation.Valid;
import lt.techin.jwt.dto.UserGetDTO;
import lt.techin.jwt.dto.UserMapper;
import lt.techin.jwt.dto.UserRequestDTO;
import lt.techin.jwt.dto.UserResponseDTO;
import lt.techin.jwt.model.User;
import lt.techin.jwt.service.UserService;
import lt.techin.jwt.util.WebUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO dto) {
        User user = userService.addUser(dto);
        UserResponseDTO responseDTO = new UserResponseDTO(user.getId(),user.getUsernamo());
        URI location = WebUtils.createLocation("/{id}",user.getId());
        return ResponseEntity.created(location).body(responseDTO);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<List<UserGetDTO>> findAllUsers() {
        List<User> users = userService.findAllUsers();
        List<UserGetDTO> dto = users.stream().map(UserMapper::userToDTO).toList();

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    public ResponseEntity<User> findUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }


}
