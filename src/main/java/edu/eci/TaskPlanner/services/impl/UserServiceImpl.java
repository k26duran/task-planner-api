package edu.eci.TaskPlanner.Services.Impl;

import edu.eci.TaskPlanner.Model.User;
import edu.eci.TaskPlanner.Services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private Map<String, User> users = new HashMap<>();

    @Override
    public List<User> getUsersList() {
        List<User> usersList = new ArrayList<>();
        for (String userId : users.keySet()) {
            usersList.add(users.get(userId));
        }
        return usersList;
    }

    @Override
    public User getUserById(String userId) {
        return users.get(userId);
    }
    
    @Override
    public User getUserByEmail(String email) {
        User user = null;
        User userTemp = null;
        for (String userId : users.keySet()) {
            userTemp = users.get(userId);
            if (email.equals(userTemp.getEmail())) {
                user = userTemp;
                break;
            }
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;
        User userTemp = null;
        for (String userId : users.keySet()) {
            userTemp = users.get(userId);
            if (username.equals(userTemp.getUsername())) {
                user = userTemp;
                break;
            }
        }
        return user;
    }
    @Override
    public User createUser(User user) {
        user.setId(users.size() + 1);
        String userId = String.valueOf(user.getId());
        users.put(userId, user);
        return users.get(userId);
    }
    
    @Override
    public User updateUser(User user) {
        String userId = String.valueOf(user.getId());
        users.replace(userId, user);
        return users.get(userId);
    }

    @Override
    public void removeUser(String userId) {
        users.remove(userId);
    }
}
