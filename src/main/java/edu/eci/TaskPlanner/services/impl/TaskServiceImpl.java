package edu.eci.TaskPlanner.Services.Impl;

import edu.eci.TaskPlanner.Model.Task;
import edu.eci.TaskPlanner.Model.User;
import edu.eci.TaskPlanner.Services.TaskService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TaskServiceImpl implements TaskService {

    private static ConcurrentHashMap<String, Task> tasks = new ConcurrentHashMap<>();

    @Override
    public List<Task> getTasksList() {
        List<Task> tasksList = new ArrayList<>();
        for (String taskId : tasks.keySet()) {
            tasksList.add(tasks.get(taskId));
        }
        return tasksList;
    }

    @Override
    public Task getTaskById(String taskId) {
        return tasks.get(taskId);
    }

    @Override
    public List<Task> getTasksByUserId(String userId) {
        List<Task> tasksList = new ArrayList<>();
        for (String taskId : tasks.keySet()) {
            Task task = tasks.get(taskId);
            if (task.getResponsible() != null) {
                if (task.getResponsible().getId() == Integer.parseInt(userId)){
                    tasksList.add(task);
                }
            }
        }
        return tasksList;
    }

    @Override
    public Task assignTaskToUser(String taskId, User user) {
        tasks.get(taskId).setResponsible(user);
        return tasks.get(taskId);
    }

    @Override
    public Task createTask(Task task) {
        task.setId(tasks.size() + 1);
        String taskId = String.valueOf(task.getId());
        tasks.put(taskId, task);
        return tasks.get(taskId);
    }

    @Override
    public void removeTask(String taskId) {
        tasks.remove(taskId);
    }

    @Override
    public Task updateTask(Task task) {
        String taskId = String.valueOf(task.getId());
        tasks.replace(taskId, task);
        return tasks.get(taskId);
    }
}
