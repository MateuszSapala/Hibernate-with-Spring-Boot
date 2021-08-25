package com.sapalamateusz.HibernatewithSpringBoot.service;

import com.sapalamateusz.HibernatewithSpringBoot.dao.UserRepository;
import com.sapalamateusz.HibernatewithSpringBoot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    @Override
    public User saveUser(User user){
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void changeUser(User newUser, Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            newUser.setId(id);
            saveUser(newUser);
            return;
        }
        user.get().setName(newUser.getName());
        user.get().setRole(newUser.getRole());
    }
}
