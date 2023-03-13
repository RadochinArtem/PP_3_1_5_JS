package ru.kata.spring.boot_security.demo.configs;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
@Service
public class InitDb {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public InitDb(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {

        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        if(roleRepository.count() == 0) {
            roleRepository.save(roleAdmin);
            roleRepository.save(roleUser);
            List<Role> rolesForAdmin = new ArrayList<>();
            List<Role> rolesForUser = new ArrayList<>();
            rolesForAdmin.add(roleAdmin);
            rolesForAdmin.add(roleUser);
            rolesForUser.add(roleUser);
            User admin = new User();
            admin.setFirstName("admin");
            admin.setLastName("admin");
            admin.setAge(21);
            admin.setEmail("admin");
            admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
            admin.setRoles(rolesForAdmin);
            User user = new User ();
            user.setFirstName("user");
            user.setLastName("user");
            user.setAge(21);
            user.setEmail("user");
            user.setPassword(new BCryptPasswordEncoder().encode("user"));
            user.setRoles(rolesForUser);
            userRepository.save(admin);
            userRepository.save(user);
        }
    }
}
