package lt.techin.media_site.repository;

import lt.techin.media_site.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Byte> {
    Role findByName(String name);
}
