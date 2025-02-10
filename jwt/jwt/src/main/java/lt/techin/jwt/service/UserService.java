package lt.techin.jwt.service;

import lt.techin.jwt.dto.UserMapper;
import lt.techin.jwt.dto.UserRequestDTO;
import lt.techin.jwt.model.Role;
import lt.techin.jwt.model.User;
import lt.techin.jwt.repository.UserRepository;
import lt.techin.jwt.validation.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder,RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User with email '" + email + "' was not found"));
    }

    public User addUser(UserRequestDTO dto) {
        User user = UserMapper.userToEntity(dto);
        Role roleAdmin = roleService.findRoleByName("ROLE_ADMIN");
        Role roleUser = roleService.findRoleByName("ROLE_USER");
        if (userRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("email is already in use");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getEmail().equalsIgnoreCase("some@thing.com")) {
            user.getRoles().add(roleAdmin);
            user.getRoles().add(roleUser);
        } else {
            user.getRoles().add(roleUser);
        }
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    public User findUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id '" + id + "' was " +
                "not found"));
    }

}
