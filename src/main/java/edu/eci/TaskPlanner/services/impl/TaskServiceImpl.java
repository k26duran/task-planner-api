package edu.eci.TaskPlanner.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import edu.eci.TaskPlanner.entities.Task;
import edu.eci.TaskPlanner.entities.User;
import edu.eci.TaskPlanner.services.TaskService;

@Component
public class TaskServiceImpl implements TaskService {
	
	private Map<Integer, Task> tasks = new HashMap<>();
	
	@Override
	public Task getTaskById(int taskId) {
		return tasks.get(taskId);
	}

	@Override
	public List<Task> getTasksByUser(int UserId) {
		 List<Task> list = new ArrayList<>();
	        for (int id : tasks.keySet()) {
	            Task task = tasks.get(id);
	            if (task.getUser().getId() == UserId){
	               list.add(task);
	            }
	        }
	        return list;
	}

	@Override
	public List<Task> getTaskByUsername(String Username) {
		List<Task> list = new ArrayList<>();
        for (int id : tasks.keySet()) {
            Task task = tasks.get(id);
            if (task.getUser().getUsername() == Username){
               list.add(task);
            }
        }
        return list;
	}

	@Override
	public List<Task> getTasks() {
		List<Task> list = new ArrayList<>();
        for (int id : tasks.keySet()) {
            list.add(tasks.get(id));
        }
        return list;
	}

	@Override
	public Task modifyTaskUser(int idTask, User user) {
		tasks.get(idTask).setUser(user);
        return tasks.get(idTask);
	}

	@Override
	public Task newTask(Task task) {
		int id=task.getId();
		tasks.put(id, task);
		return tasks.get(id);
	}

	@Override
	public Task updateTask(Task newtask) {
		int id=newtask.getId();
		tasks.replace(id, newtask);
		return tasks.get(id);
	}

	@Override
	public Task removeTask(int taskId) {
		Task t= tasks.get(taskId);
		tasks.remove(taskId);
		return t;
	}

}
