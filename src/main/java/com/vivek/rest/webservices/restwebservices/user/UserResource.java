package com.vivek.rest.webservices.restwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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
