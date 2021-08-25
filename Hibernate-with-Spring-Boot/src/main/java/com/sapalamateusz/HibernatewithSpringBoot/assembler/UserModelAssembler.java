package com.sapalamateusz.HibernatewithSpringBoot.assembler;

import com.sapalamateusz.HibernatewithSpringBoot.entity.User;
import com.sapalamateusz.HibernatewithSpringBoot.rest.UserController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>>
{
    @Override
    public EntityModel<User> toModel(User user) {
        return EntityModel.of(user,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getUserById(user.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getAllUser()).withRel("Get all users"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).changeUser(user, user.getId())).withRel("Change user"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).deleteUser(user.getId())).withRel("Delete user"));
    }
}
