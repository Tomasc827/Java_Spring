package lt.techin.cat_cafe_shop.controller;


import jakarta.validation.Valid;
import lt.techin.cat_cafe_shop.dto.user.UserRequestDTO;
import lt.techin.cat_cafe_shop.dto.user.UserResponseDTO;
import lt.techin.cat_cafe_shop.model.User;
import lt.techin.cat_cafe_shop.service.UserService;
import lt.techin.cat_cafe_shop.util.WebUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO dto) {
        User user = userService.createUser(dto);
        UserResponseDTO responseDTO = new UserResponseDTO(user.getId(),
                user.getName(),
                LocalDateTime.now(),
                user.getRoles());

        URI location = WebUtils.createLocation("/{id}", user.getId());

        return ResponseEntity.created(location).body(responseDTO);
    }
}
