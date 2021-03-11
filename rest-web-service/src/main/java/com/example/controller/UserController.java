package com.example.controller;

import com.example.model.User;
import com.example.service.UserDaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    //to get all user from database
    @GetMapping(path = "/")
    public List<User> getAllUsers(){
        return userDaoService.findAllUser();
    }

    //geting value of specefic user by it's user id
    @GetMapping(path = "/{id}")
    public User getUserById(@PathVariable int id){
        return userDaoService.findById(id);
    }

    //saving user and return status code created
    //also return the url of the save user
    @PostMapping(path = "/save")
    public ResponseEntity<Object> createdUser(@RequestBody User user){
        userDaoService.save(user);

        //give a response called created when user save the user
        //
        URI uri = ServletUriComponentsBuilder
                //it's return the current url like /users/save/
                .fromCurrentRequest()
                //after taking the request is like /users/save/{id}
                .path("/{id}")
                //after taking the request is like /users/save/5
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
