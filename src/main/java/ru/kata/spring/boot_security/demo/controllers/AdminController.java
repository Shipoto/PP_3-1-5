//package ru.kata.spring.boot_security.demo.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import ru.kata.spring.boot_security.demo.service.UserServiceImpl;
//
//@Controller
//public class AdminController {
//    @Autowired
//    private UserServiceImpl userServiceImpl;
//
//    @GetMapping("/admin")
//    public String userList(Model model) {
//        model.addAttribute("allUsers", userServiceImpl.listUsers());
//        return "admin";
//    }
//
//    @PostMapping("/admin")
//    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Integer userId,
//                              @RequestParam(required = true, defaultValue = "" ) String action,
//                              Model model) {
//        if (action.equals("delete")){
//            userServiceImpl.delete(userId);
//        }
//        return "redirect:/admin";
//    }
//
////    @GetMapping("/admin/gt/{userId}")
////    public String  gtUser(@PathVariable("userId") Integer userId, Model model) {
////        model.addAttribute("allUsers", userService.listUsers(userId));
////        return "admin";
////    }
//}
