package main.java.services.DBServices;

import main.java.databank.accounts.Account;
import main.java.databank.accounts.Role;
import main.java.databank.accounts.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByName(String name) {
        if (roleRepository.existsById(name)) return roleRepository.getReferenceById(name);
        return null;
    }

    public void save(Role role) {
        roleRepository.save(role);
    }
}
