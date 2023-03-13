package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",unique = true, length = 100)
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
    public Role() {
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getAuthority() {
        return name;
    }
    public String getStringRole() {
        return name.substring(5);
    }
}