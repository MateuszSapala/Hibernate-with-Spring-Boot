package com.sapalamateusz.HibernatewithSpringBoot.rest;

import com.sapalamateusz.HibernatewithSpringBoot.assembler.UserModelAssembler;
import com.sapalamateusz.HibernatewithSpringBoot.entity.User;
import com.sapalamateusz.HibernatewithSpringBoot.exceptions.UserNotFoundException;
import com.sapalamateusz.HibernatewithSpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserModelAssembler userModelAssembler;

    @Autowired
    public UserController(UserService userService, UserModelAssembler userModelAssembler) {
        this.userService = userService;
        this.userModelAssembler = userModelAssembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<User>> getAllUser(){
        List<EntityModel<User>> users = userService.getAllUser().stream().map(userModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(users, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getAllUser()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<User> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id).orElseThrow(() -> new UserNotFoundException(id));
        return userModelAssembler.toModel(user);
    }
}
