//package ru.kata.spring.boot_security.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import ru.kata.spring.boot_security.demo.controllers.MyController;
//import ru.kata.spring.boot_security.demo.entity.User;
//import ru.kata.spring.boot_security.demo.repository.UserRepository;
//
//import java.util.Optional;
//
//@Service
//public class MyUserDetailService implements UserDetailsService {
//
//    private UserRepository userRepository;
//
//    @Autowired
//    public MyUserDetailService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByUserName(username);
////        Optional<User> user = userRepository.findByUserName(username);
////
////        if (user.isEmpty()) {
////            throw new UsernameNotFoundException("User not found");
////        } else {
////            return userRepository.findByUserName(user.get());
////        }
//    }
//}
