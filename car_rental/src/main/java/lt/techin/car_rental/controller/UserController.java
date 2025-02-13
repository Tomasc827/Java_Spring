package lt.techin.car_rental.controller;


import jakarta.validation.Valid;
import lt.techin.car_rental.dto.userDTO.UserRequestDTO;
import lt.techin.car_rental.dto.userDTO.UserResponseDTO;
import lt.techin.car_rental.model.User;
import lt.techin.car_rental.service.UserService;
import lt.techin.car_rental.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDTO dto) {
        User user = userService.createUser(dto);
        UserResponseDTO responseDTO = new UserResponseDTO(user.getId(),
                user.getName(),
                user.isHasLicense());

        URI location = WebUtils.createLocation("/{id}", user.getId());

        return ResponseEntity.created(location).body(responseDTO);
    }

}
