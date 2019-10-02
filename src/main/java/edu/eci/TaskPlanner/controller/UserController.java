package edu.eci.TaskPlanner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.TaskPlanner.entities.User;
import edu.eci.TaskPlanner.services.UserService;

@RestController
@RequestMapping(value = "/taskPlanner")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/v1/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/v1/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user = userService.getUserById(Integer.parseInt(userId));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/v1/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User userCreated = userService.addUser(user);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

    @PutMapping(value = "/v1/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User userUpdated = userService.updateUser(user);
        return new ResponseEntity<>(userUpdated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/v1/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        User u= userService.deleteUser(Integer.parseInt(userId));
        return new ResponseEntity<>("Removed user: "+u, HttpStatus.OK);
    }
    }
