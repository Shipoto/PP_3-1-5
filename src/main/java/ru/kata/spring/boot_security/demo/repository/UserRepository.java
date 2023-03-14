package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @EntityGraph(value = "user-entity-graph")
    @Query("select u from User u where u.userName = :userName")
    /*Optional*/User findByUserName(String userName);
}
