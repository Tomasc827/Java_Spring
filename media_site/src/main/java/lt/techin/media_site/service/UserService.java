package lt.techin.media_site.service;


import lt.techin.media_site.dto.user.UserMapper;
import lt.techin.media_site.dto.user.UserPostDTO;
import lt.techin.media_site.model.Role;
import lt.techin.media_site.model.User;
import lt.techin.media_site.repository.RoleRepository;
import lt.techin.media_site.repository.UserRepository;
import lt.techin.media_site.validation.exception.EmailTakenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User createUser(UserPostDTO dto) {
        User newUser = UserMapper.toEntity(dto);
        if(userRepository.existsByEmail(dto.email())) {
            throw new EmailTakenException("The email '" + dto.email() + "' is taken");
        }
        if(newUser.getImageURL() == null) {
            newUser.setImageURL("https://upload.wikimedia.org/wikipedia/en/8/86/Avatar_Aang.png");
        }
        Role role = roleRepository.findByName(dto.email().equals("some@thing.com") ? "ROLE_ADMIN" : "ROLE_USER");
        newUser.getRoles().add(role);

        newUser.setPassword(passwordEncoder.encode(dto.password()));
        userRepository.save(newUser);
        return newUser;
    }
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


}
