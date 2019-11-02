package edu.eci.TaskPlanner.Controllers;

import edu.eci.TaskPlanner.Config.Token;
import edu.eci.TaskPlanner.Model.User;
import edu.eci.TaskPlanner.Services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

@RestController
@RequestMapping(value = "/taskPlanner")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/v1/user/login", method = RequestMethod.POST)
    public Token login(@RequestBody User userLogin) throws ServletException {
        String jwtToken = "";
        if ((userLogin.getEmail() == null && userLogin.getUsername() == null) || userLogin.getPassword() == null) {
            throw new ServletException("Please fill in email or username, and password");
        }
        String email = userLogin.getEmail();
        String username = userLogin.getUsername();
        String password = userLogin.getPassword();
        User user = null;
        if (email != null) {
            user = userService.getUserByEmail(email);
        } else if (username != null) {
            user = userService.getUserByUsername(username);
        }
        if (user == null) {
            throw new ServletException("User not found.");
        }
        String pwd = user.getPassword();
        if (!password.equals(pwd)) {
            throw new ServletException("Invalid login. Please check your name and password.");
        }
        jwtToken = Jwts.builder().setSubject(username).claim("roles", "user").setIssuedAt(new Date()).signWith(
                SignatureAlgorithm.HS256, "secretkey").compact();
        return new Token(jwtToken);
    }
    
    @RequestMapping(value = "/v1/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsersList() {
        List<User> users = userService.getUsersList();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/users/usernameEmail/{userNameEmail}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByUsernameEmail(@PathVariable String userNameEmail) {
        User user = null;
        if (userNameEmail.contains("@")) {
            user = userService.getUserByEmail(userNameEmail);
        } else {
            user = userService.getUserByUsername(userNameEmail);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/user/register", method = RequestMethod.POST)
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User userCreated = userService.createUser(user);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
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
