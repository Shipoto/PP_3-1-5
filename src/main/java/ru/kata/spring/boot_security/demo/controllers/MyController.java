package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl.*;

@Controller
public class MyController {
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public MyController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/")
    public String plane(){

        return "/admin";
    }

    @GetMapping("/user")
    public String user(){

        return "/user";
    }

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("listUsers", userServiceImpl.listUsers());

        return "/admin";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userServiceImpl.getOneUser(id));
        return "/user";
    }

    @GetMapping(value = "/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userServiceImpl.getOneUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userServiceImpl.update(id, user);
        return "redirect:/{id}";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/add-user")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "/add-user";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userServiceImpl.add(user);
        return "redirect:/{id}";
    }

//    @GetMapping("/admin")
//    public String admin(){
//        return "admin";
//    }

}
