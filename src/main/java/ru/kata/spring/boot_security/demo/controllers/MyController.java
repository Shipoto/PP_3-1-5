package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class MyController {
    private UserServiceImpl userServiceImpl;

    @Autowired
    public MyController(UserServiceImpl userServiceImpl) {

        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal){
        model.addAttribute("user", userServiceImpl.getOneUser(1));
        return "user";
    }

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("listUsers", userServiceImpl.listUsers());
        return "admin/admin";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userServiceImpl.getOneUser(id));
        return "user";
    }

    @GetMapping(value = "admin/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userServiceImpl.getOneUser(id));
        return "/admin/edit";
    }

    @PatchMapping("admin/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userServiceImpl.update(user);
        return "redirect:/admin";
    }

    @GetMapping()
    public String index() {
        return "index";
    }

    @GetMapping("admin/add-user")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "admin/add-user";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userServiceImpl.add(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable("id") int id) {
        userServiceImpl.delete(id);
        return "redirect:/admin";
    }
}
