package lt.techin.jwt.util;


import lt.techin.jwt.model.Role;
import lt.techin.jwt.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddRoles {
    private final RoleRepository roleRepository;

    @Autowired
    public AddRoles(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            if(roleRepository.count() == 0) {
                Role adminRole = new Role();
                adminRole.setName("ROLE_ADMIN");

                Role userRole = new Role();
                userRole.setName("ROLE_USER");

                roleRepository.save(adminRole);
                roleRepository.save(userRole);

                System.out.println("Roles initialized");
            }

        };
    }

}
