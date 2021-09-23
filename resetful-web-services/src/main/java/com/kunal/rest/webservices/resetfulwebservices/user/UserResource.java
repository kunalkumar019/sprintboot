package com.kunal.rest.webservices.resetfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilderDsl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserResource {

    @Autowired
    private  UserDaoService service;
    //retriveAllUsers

    @GetMapping("/users")
    public List<User> retriveAllUsers(){

        return service.findAll();
    }

    //retriveUser(int id)
    @GetMapping("/users/{id}")
    public User retriveUser(@PathVariable int id)
    {
     User user = service.findOne(id);
     if (user == null)
         throw new UserNotFoundExeception("id-" + id);
     return user;
    }

    //use of heteous
    @GetMapping("/users1/{id}")
    public EntityModel<User> retriveUserEntity(@PathVariable int id)
    {
        User user = service.findOne(id);
        if (user == null)
            throw new UserNotFoundExeception("id-" + id);

        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).retriveAllUsers());
        model.add(linkToUsers.withRel("all-users"));
        return model;
    }

    //deleteUser(int id)
    @DeleteMapping("/users/{id}")
    public void deleteById(@PathVariable int id)
    {
        User user = service.deleteById(id);
        if (user == null)
            throw new UserNotFoundExeception("id-" + id);

    }

    //i/p - details of new user to be created
    //output - return created URI
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
    User savedUser = service.save(user);

    URI location=
    ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("{/id}")
            .buildAndExpand(savedUser.getId()).toUri();

    return ResponseEntity.created(location).build();
    }

}
