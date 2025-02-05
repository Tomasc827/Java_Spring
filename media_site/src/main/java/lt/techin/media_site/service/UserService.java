package lt.techin.media_site.service;


import lt.techin.media_site.dto.user.UserMapper;
import lt.techin.media_site.dto.user.UserPostDTO;
import lt.techin.media_site.model.User;
import lt.techin.media_site.model.UserRole;
import lt.techin.media_site.repository.UserRepository;
import lt.techin.media_site.validation.exception.EmailTakenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(UserPostDTO dto) {
        User newUser = UserMapper.toEntity(dto);
        if(userRepository.existsByEmail(dto.email())) {
            throw new EmailTakenException("The email '" + dto.email() + "' is taken");
        }
        if(newUser.getImageURL() == null) {
            newUser.setImageURL("https://upload.wikimedia.org/wikipedia/en/8/86/Avatar_Aang.png");
        }
        newUser.setUserRole(UserRole.REGULAR);
        newUser.setPassword(passwordEncoder.encode(dto.password()));
        userRepository.save(newUser);
        return newUser;
    }
}
