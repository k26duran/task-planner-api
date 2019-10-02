package edu.eci.TaskPlanner.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.eci.TaskPlanner.entities.Task;
import edu.eci.TaskPlanner.entities.User;
import edu.eci.TaskPlanner.services.UserService;

public class UserServiceImpl implements UserService{

	private Map<Integer, User> users = new HashMap<>();
		
	
	@Override
	public List<User> getUsers() {
		List<User> list = new ArrayList<>();
        for (int id : users.keySet()) {
            list.add(users.get(id));
        }
        return list;
	}

	@Override
	public User getUserById(int userId) {
		return users.get(userId);
	}

	@Override
	public User getUserByUsername(String username) {
		for (int id : users.keySet()) {
			if (users.get(id).getUsername()== username) {
				return users.get(id);
			}
        }
		return null;
	}

	@Override
	public User addUser(User user) {
		int id=user.getId();
		users.put(id, user);
		return users.get(id);
	}

	@Override
	public User updateUser(User user) {
		int id=user.getId();
		users.replace(id, user);
		return users.get(id);
	}

	@Override
	public User deleteUser(int userId) {
		User temp= users.get(userId);
		users.remove(userId);
		return temp;
	}

}
