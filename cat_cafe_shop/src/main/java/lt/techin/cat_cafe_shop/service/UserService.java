package lt.techin.cat_cafe_shop.service;


import lt.techin.cat_cafe_shop.dto.user.UserMapper;
import lt.techin.cat_cafe_shop.dto.user.UserRequestDTO;
import lt.techin.cat_cafe_shop.model.Role;
import lt.techin.cat_cafe_shop.model.User;
import lt.techin.cat_cafe_shop.repository.RoleRepository;
import lt.techin.cat_cafe_shop.repository.UserRepository;
import lt.techin.cat_cafe_shop.validation.exception.EmailInUseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(UserRequestDTO dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new EmailInUseException("Email '" + dto.email() + "' is already in use");
        }

        User user = UserMapper.userToEntity(dto);
        Role role = roleRepository.findByName("ROLE_USER");

        user.getRoles().add(role);
        user.setPassword(passwordEncoder.encode(dto.password()));
        return userRepository.save(user);
    }
}
