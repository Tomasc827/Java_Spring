package lt.techin.jwt.service;


import lt.techin.jwt.model.Role;
import lt.techin.jwt.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRoleByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new IllegalArgumentException("no such role"));
    }
}
