package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@CrossOrigin
@Controller
@RequestMapping("/user")
public class ControllerRestUser {

    @Secured(value = {"ROLE_USER"})
    @GetMapping
    public String findAll() {
        return "admin/rest-user";
    }
}
