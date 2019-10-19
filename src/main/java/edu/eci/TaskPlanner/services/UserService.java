package edu.eci.TaskPlanner.Services;

import edu.eci.TaskPlanner.Model.User;

import java.util.List;

public interface UserService {

    List<User> getUsersList();

    User getUserById(String userId);

    User createUser(User user);

    User updateUser(User user);

    void removeUser(String userId);
}
