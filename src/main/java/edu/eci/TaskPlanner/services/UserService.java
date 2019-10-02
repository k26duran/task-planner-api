package edu.eci.TaskPlanner.services;

import java.util.List;

import org.springframework.stereotype.Service;
import edu.eci.TaskPlanner.entities.User;

@Service
public interface UserService {
	
	List<User> getUsers();
	User getUserById(int userId);
	User getUserByUsername(String username);
	User addUser(User user);
	User updateUser(User user);
	User deleteUser(int userId);
	
}
