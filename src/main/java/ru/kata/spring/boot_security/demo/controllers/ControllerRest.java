package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.List;

//@CrossOrigin
@RequestMapping("/api/users")
@RestController
public class ControllerRest {
    private UserServiceImpl userServiceImpl;

    @Autowired
    public ControllerRest(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> rest(){
        return new ResponseEntity<>(userServiceImpl.listUsers(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> user(Principal principal){
        User user = (User) userServiceImpl.loadUserByUsername(principal.getName());
        System.out.println(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        return new ResponseEntity<>(userServiceImpl.getOneUser(id), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Void>  editUser(@RequestBody User user) {
        userServiceImpl.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
        userServiceImpl.add(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") int id) {
        userServiceImpl.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
