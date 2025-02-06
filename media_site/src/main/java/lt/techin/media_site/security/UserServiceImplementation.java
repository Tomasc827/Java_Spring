package lt.techin.media_site.security;


import lt.techin.media_site.model.User;
import lt.techin.media_site.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserServiceImplementation(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User foundUser = userService.findUserByEmail(email);
        if (foundUser == null) {
            throw new UsernameNotFoundException("User with email '" + email + "' does not exist");
        }
        return foundUser;
    }
}
