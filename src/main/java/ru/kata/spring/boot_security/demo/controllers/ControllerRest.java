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

@CrossOrigin
@RequestMapping("/api/users")
@RestController
public class ControllerRest {
    private UserServiceImpl userServiceImpl;

    @Autowired
    public ControllerRest(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<User>> rest(){
        return new ResponseEntity<>(userServiceImpl.listUsers(), HttpStatus.OK);
//        return "/admin/users";
    }

//    @GetMapping("/index")
//    public String index() {
//        return "index";
//    }

    @GetMapping("/user")
    public ResponseEntity<User> user(/*Principal principal*/){
//        User user = (User) userServiceImpl.loadUserByUsername(principal.getName());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

//    @GetMapping("/admin")
//    public String admin(Model model){
//        model.addAttribute("listUsers", userServiceImpl.listUsers());
//        return "admin/";
//    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        return new ResponseEntity<>(userServiceImpl.getOneUser(id), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Void>  editUser(@RequestBody User user) {
        userServiceImpl.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PatchMapping("admin/{id}")
//    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
//        userServiceImpl.update(user);
//        return "redirect:/users";
//    }

//    @GetMapping("admin/add-user")
//    public String addUser() {
//        return "admin/add-user";
//    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userServiceImpl.add(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") int id) {
        userServiceImpl.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
