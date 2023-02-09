package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entity.User;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getUsers() {
        TypedQuery<User> query= entityManager.createQuery(
                "SELECT user from User user", User.class);
        return query.getResultList();
    }

    @Override
    public User getOneUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(getOneUser(id));
    }

    public void update(int id, User user) {
        User userForUpdate = getOneUser(id);
        userForUpdate.setName(user.getName());
        userForUpdate.setSurName(user.getSurName());
        userForUpdate.setAge(user.getAge());
        userForUpdate.setDepartment(user.getDepartment());
        add(userForUpdate);
    }
}
