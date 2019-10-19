package edu.eci.TaskPlanner.Controllers;

import edu.eci.TaskPlanner.Model.User;
import edu.eci.TaskPlanner.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/taskPlanner")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/v1/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsersList() {
        List<User> users = userService.getUsersList();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/users/{userId}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/users", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User userCreated = userService.createUser(user);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/v1/users", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User userUpdated = userService.updateUser(user);
        return new ResponseEntity<>(userUpdated, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/v1/users/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        userService.removeUser(userId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
