package lt.techin.jwt.security;

import lt.techin.jwt.model.User;
import lt.techin.jwt.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserDetailsService {

    private UserService userService;

    public UserServiceImplementation(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User foundUser = userService.findUserByEmail(email);
            if(foundUser == null) {
                throw new UsernameNotFoundException("err");
            }

        return foundUser;
    }
}
