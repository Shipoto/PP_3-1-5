package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserDAO {
    void add(User user);
    public List<User> getUsers();
    public User getOneUser(int id);
    void delete(int id);
    void update(int id, User user);

}
