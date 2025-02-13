package lt.techin.car_rental.util;


import lt.techin.car_rental.model.Role;
import lt.techin.car_rental.model.User;
import lt.techin.car_rental.repository.RoleRepository;
import lt.techin.car_rental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
public class RolesInitialize {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RolesInitialize(RoleRepository roleRepository,UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner initRolesDatabase() {
        return args -> {
            if(roleRepository.count() == 0) {
                Role roleAdmin = new Role();
                roleAdmin.setName("ROLE_ADMIN");

                Role roleUser = new Role();
                roleUser.setName("ROLE_USER");

                roleRepository.save(roleAdmin);
                roleRepository.save(roleUser);

                System.out.println("Roles initialized in database - admin and user role");
            }
            if(!userRepository.existsByEmail("admin@admin.com")) {
                User user = new User();
                user.setName("admin");
                user.setEmail("admin@admin.com");
                user.setPassword(passwordEncoder.encode("Something9!"));
                user.setHasLicense(true);
                user.setAge(LocalDate.of(1990, 1, 1));
                Role roleAdmin = roleRepository.findByName("ROLE_ADMIN").orElseThrow();
                Role roleUser = roleRepository.findByName("ROLE_USER").orElseThrow();
                user.getRoles().add(roleAdmin);
                user.getRoles().add(roleUser);
                userRepository.save(user);
            }
        };

    }

}
