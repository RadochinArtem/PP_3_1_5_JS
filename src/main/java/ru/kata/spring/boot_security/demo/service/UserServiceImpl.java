package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(user.getPassword());
        userRepository.save(user);
    }


    @Override
    @Transactional
    public void updateUser(User user, Long id) {
        user.setId(id);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    @Override
    public User findUserById(Long id) {
       return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }


    @PostConstruct
    @Override
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
            admin.setPassword("admin");
            admin.setRoles(rolesForAdmin);
            User user = new User ();
            user.setFirstName("user");
            user.setLastName("user");
            user.setAge(21);
            user.setEmail("user");
            user.setPassword("user");
            user.setRoles(rolesForUser);
            userRepository.save(admin);
            userRepository.save(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findUserByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", email));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                roleService.mapRolesToAuthorities(user.getRoles()));
    }
}
