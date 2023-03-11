package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers ();
    void saveUser (User user);
    void updateUser (User user, Long id);
    void deleteUserById (Long id);

    User findUserByEmail (String Email);

    User findUserById (Long id);

    void init();

}
