package com.vivek.rest.webservices.restwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;
    //retrieve all user
    @RequestMapping(method = RequestMethod.GET, path = "/users")
    public List<User> retrieveAllUser(){
        return (service.findAll());
    }

    // retrieveUserById  int id
    @GetMapping(path = "/users/{id}")
    public User retrieveUserById(@PathVariable int id) {
        User user = service.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("id - " + id);
        }

        return user;
    }
//
//    @PostMapping(path = "/users/add")
//    public User AddUser () {
//
//    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
       User savedUser = service.save(user);

       // Best practise is to return that the resourcec is created
       // and send the uri of the creasted
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
