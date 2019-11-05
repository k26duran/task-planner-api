package edu.eci.TaskPlanner.Services.Impl;

import edu.eci.TaskPlanner.Model.Task;
import edu.eci.TaskPlanner.Model.User;
import edu.eci.TaskPlanner.Persistence.TaskRepository;
import edu.eci.TaskPlanner.Services.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
    private TaskRepository taskRepository;
	
    //private static ConcurrentHashMap<String, Task> tasks = new ConcurrentHashMap<>();

    @Override
    public List<Task> getTasksList() {
       /* List<Task> tasksList = new ArrayList<>();
        for (String taskId : tasks.keySet()) {
            tasksList.add(tasks.get(taskId));
        }*/
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(String taskId) {
    	return taskRepository.findById(Integer.parseInt(taskId)); 
    	//tasks.get(taskId);
    }

    @Override
    public List<Task> getTasksByUserId(String userId) {
        /*
    	List<Task> tasksList = new ArrayList<>();
        for (String taskId : tasks.keySet()) {
            Task task = tasks.get(taskId);
            if (task.getResponsible() != null) {
                if (task.getResponsible().getId() == Integer.parseInt(userId)){
                    tasksList.add(task);
                }
            }
        }*/
        return taskRepository.findByResponsible(Integer.parseInt(userId));
    }

    @Override
    public Task assignTaskToUser(String taskId, User user) {
        //tasks.get(taskId).setResponsible(user);
    	Task newTask =taskRepository.findById(Integer.parseInt(taskId));
    	newTask.setResponsible(user);
    	taskRepository.save(newTask);
        return newTask;
    }

    @Override
    public Task createTask(Task task) {
        /*
         task.setId(tasks.size() + 1);
        String taskId = String.valueOf(task.getId());
        tasks.put(taskId, task);
        */
    	task.setId((int)taskRepository.count()+1);
    	
        return taskRepository.save(task);
    }

    @Override
    public void removeTask(String taskId) {
        //tasks.remove(taskId);
    	taskRepository.deleteById(Integer.parseInt(taskId));
    }

    @Override
    public Task updateTask(Task task) {
    	/*String taskId = String.valueOf(task.getId());
        tasks.replace(taskId, task);*/
    	taskRepository.save(task);
        return task;
    }
}
