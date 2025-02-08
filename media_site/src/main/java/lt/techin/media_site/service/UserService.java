package lt.techin.media_site.service;


import lt.techin.media_site.dto.user.UserGetDTO;
import lt.techin.media_site.dto.user.UserMapper;
import lt.techin.media_site.dto.user.UserPatchDTO;
import lt.techin.media_site.dto.user.UserPostDTO;
import lt.techin.media_site.model.Role;
import lt.techin.media_site.model.User;
import lt.techin.media_site.repository.RoleRepository;
import lt.techin.media_site.repository.UserRepository;
import lt.techin.media_site.validation.exception.AccountOptionsException;
import lt.techin.media_site.validation.exception.EmailTakenException;
import lt.techin.media_site.validation.exception.IdDoesNotExistException;
import lt.techin.media_site.validation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        return userRepository.findByEmail(email).orElseThrow(() -> new  NotFoundException("User with email '" + email +
                "'" +
                " not found"));
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


    public void deleteUser(long id) {
        User user =
                userRepository.findById(id).orElseThrow(() -> new IdDoesNotExistException("User with id '" + id + "' " +
                        "does not exist"));
        userRepository.delete(user);
    }
    public void userDeleteSelf(long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser =
                userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new NotFoundException("User " +
                        "with id '" + id + "' not found"));

        if(currentUser.getId() != id) {
            throw new AccountOptionsException("Can only delete your own account") ;
        }
        userRepository.delete(currentUser);
    }

    public User patchUserById(UserPatchDTO dto, long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser =
                userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new NotFoundException("User " +
                        "with id'" + id + "' not found"));
        if (currentUser.getId() != id) {
            throw new AccountOptionsException("Can only update details of your own account");
        }
        if (dto.username() != null) {
            currentUser.setUsername(dto.username());
        }
        if (dto.password() != null) {
            currentUser.setPassword(passwordEncoder.encode(dto.password()));
        }
        if (dto.imageURL() != null) {
            currentUser.setImageURL(dto.imageURL());
        }


        return userRepository.save(currentUser);

    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<UserGetDTO> getUserList() {
        return UserMapper.getToEntity(findAllUsers());
    }

}
