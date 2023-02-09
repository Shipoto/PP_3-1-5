package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@Transactional(readOnly = true)
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserDAO userDao;

//    @Autowired
//    public UserServiceImpl(UserDAO userDao) {
//        this.userDao = userDao;
//    }


//    @PersistenceContext
//    private EntityManager entityManager;

    @Autowired
    UserRepository userRepository;



    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Autowired
//    public void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return user;
//        return new org.springframework.security.core.userdetails.User(
//                user.getUserName(),
//                user.getPassword(),
//                mapRolesToAuthorities(user.getRoles()));
    }

    @Transactional
    public void add(User user) {
        userDao.add(user);
    }
    // my
//    @Override
//    @Transactional(readOnly = true)
//    public List<User> listUsers() {
//        return userDao.getUsers();
//    }
    // other
    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return userRepository.findAll();
    }

//    @Override
    @Transactional(readOnly = true)
    public User getOneUser(int id) {
        return userDao.getOneUser(id);
    }

//    @Override
    @Transactional
    public void delete(int id) {
        userDao.delete(id);
    }

//    @Override
    @Transactional
    public void update(int id, User user) {
        userDao.update(id, user);
    }



    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUserName(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
}
