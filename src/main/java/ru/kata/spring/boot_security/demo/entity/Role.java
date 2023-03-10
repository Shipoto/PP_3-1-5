package ru.kata.spring.boot_security.demo.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Table(name = "t_role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;


    @Transient
    @ManyToMany(mappedBy = "roles")
    private Collection <User> users;

    public Role() {
    }

    public Role(int id) {
//        super();
        this.id = id;
    }

    public Role(int id, String name) {
//        super();
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return getName();
    }

    public static Collection<Role> getAllRoles() {
        return Arrays.asList(
                new Role(1, "ROLE_ADMIN"),
                new Role(2, "ROLE_USER")
        );
    }
}
