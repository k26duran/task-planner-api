package edu.eci.TaskPlanner.Services.Impl;

import edu.eci.TaskPlanner.Model.User;
import edu.eci.TaskPlanner.Persistence.UserRepository;
import edu.eci.TaskPlanner.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private Map<String, User> users = new HashMap<>();
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsersList() {
       /* List<User> usersList = new ArrayList<>();
        for (String userId : users.keySet()) {
            usersList.add(users.get(userId));
        }*/
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
    	return userRepository.findById(Integer.parseInt(userId));
    	//users.get(userId);
    }
    
    @Override
    public User getUserByEmail(String email) {
        /*User user = null;
        User userTemp = null;
        for (String userId : users.keySet()) {
            userTemp = users.get(userId);
            if (email.equals(userTemp.getEmail())) {
                user = userTemp;
                break;
            }
        }*/
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserByUsername(String username) {
        /*
        User userTemp = null;
        for (String userId : users.keySet()) {
            userTemp = users.get(userId);
            if (username.equals(userTemp.getUsername())) {
                user = userTemp;
                break;
            }
        }*/
        return userRepository.findByUsername(username);
    }
    @Override
    public User createUser(User user) {
       /* user.setId(users.size() + 1);
        String userId = String.valueOf(user.getId());
        users.put(userId, user);
        */
    	user.setId((int) userRepository.count()+1);
        userRepository.save(user);
    	return user;
    }
    
    @Override
    public User updateUser(User user) {
        /*String userId = String.valueOf(user.getId());
        users.replace(userId, user);*/
        return userRepository.save(user);
    }

    @Override
    public void removeUser(String userId) {
        //users.remove(userId);
    	userRepository.deleteById(Integer.parseInt(userId));
    }
}
