package edu.eci.TaskPlanner.services;

import java.util.List;
import org.springframework.stereotype.Service;
import edu.eci.TaskPlanner.entities.Task;
import edu.eci.TaskPlanner.entities.User;

@Service
public interface TaskService {
	
	Task getTaskById(int taskId);
	List<Task> getTasksByUser(int UserId);
	List<Task> getTaskByUsername(String Username);
	List<Task> getTasks();
	Task modifyTaskUser(int idTask, User user);
	Task newTask(Task task);
	Task updateTask(Task newtask);
	Task removeTask(int taskId);

}
