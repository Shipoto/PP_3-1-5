package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.List;

//@CrossOrigin
@Controller
@RequestMapping("/admin")
public class ControllerRestAdmin {

    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping
    public String findAll() {
        return "admin/rest";
    }
}
