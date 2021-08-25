package com.sapalamateusz.HibernatewithSpringBoot.service;

import com.sapalamateusz.HibernatewithSpringBoot.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    public List<User> getAllUser();

    public Optional<User> getUserById(Long id);

    public User saveUser(User user);

    public void deleteUser(Long id);

    public User changeUser(User user, Long id);

}
