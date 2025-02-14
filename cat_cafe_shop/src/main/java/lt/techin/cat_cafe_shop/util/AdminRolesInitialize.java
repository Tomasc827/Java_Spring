package lt.techin.cat_cafe_shop.util;


import lt.techin.cat_cafe_shop.model.Role;
import lt.techin.cat_cafe_shop.model.User;
import lt.techin.cat_cafe_shop.repository.RoleRepository;
import lt.techin.cat_cafe_shop.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminRolesInitialize {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AdminRolesInitialize(RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Bean
    public CommandLineRunner initialize() {
        return args -> {
            if(roleRepository.count() == 0) {
                Role roleAdmin = new Role();
                roleAdmin.setName("ROLE_ADMIN");
                Role roleUser = new Role();
                roleUser.setName("ROLE_USER");
                roleRepository.save(roleAdmin);
                roleRepository.save(roleUser);

            }
            if (!userRepository.existsByEmail("admin@admin.com")) {
                User user = new User();
                user.setEmail("admin@admin.com");
                user.setName("admin");
                user.setPassword(passwordEncoder.encode("Something9!"));
                Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
                Role roleUser = roleRepository.findByName("ROLE_USER");
                user.getRoles().add(roleAdmin);
                user.getRoles().add(roleUser);
                userRepository.save(user);

            }


        };
    }
}
