package lt.techin.cat_cafe_shop.repository;

import lt.techin.cat_cafe_shop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Byte> {
    Role findByName(String name);
}
