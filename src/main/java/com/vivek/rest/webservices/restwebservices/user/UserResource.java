package com.vivek.rest.webservices.restwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
//import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;
    //retrieve all user
    @RequestMapping(method = RequestMethod.GET, path = "/users")
    public List<User> retrieveAllUser(){
        List<User> users = service.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("User Not Found Something Went Wrong . Please try again");
        }
        return (service.findAll());
    }

    // retrieveUserById  int id
    @GetMapping(path = "/users/{id}")
    public Resource<User> retrieveUserById(@PathVariable int id) {
        User user = service.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("id - " + id);
        }
        //"all-user", SERVER_NAME+
        //retrieveAllUsers
        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).retrieveAllUser());
        resource.add(linkTo.withRel("all-users"));

        return  resource;

//        return user;
    }

/*
    @GetMapping("/user/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("id - " + id);
        }

        //"all-user", SERVER_NAME+
        //retrieveAllUsers
        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder link =
                 linkTo(methodOn(this.getClass().retrieveAllUser));
         resource.add(linkTo.withRel("all-users"));

         return  resource;
    }
*/

//
//    @PostMapping(path = "/users/add")
//    public User AddUser () {
//
//    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
       User savedUser = service.save(user);
       if (savedUser.getId() == null) {
           throw new UserCannotInsertException("User cannot be created");
       }
       // Best practise is to return that the resource is created
       // and send the uri of the created
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User deleteUser = service.deleteById(id);
        if (deleteUser == null) {
            throw new UserNotFoundException("User cannot be Deleted id" + id);
        }
    }

}
