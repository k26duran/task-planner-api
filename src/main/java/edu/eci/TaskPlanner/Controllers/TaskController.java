package edu.eci.TaskPlanner.Controllers;

import edu.eci.TaskPlanner.Model.Task;
import edu.eci.TaskPlanner.Model.User;
import edu.eci.TaskPlanner.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/taskPlanner")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/v1/tasks", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getTasksList() {
        List<Task> tasks = taskService.getTasksList();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/tasks/{taskId}", method = RequestMethod.GET)
    public ResponseEntity<Task> getTaskById(@PathVariable String taskId) {
        Task task = taskService.getTaskById(taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/users/{userId}/tasks", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable String userId) {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/users/tasks/{taskId}", method = RequestMethod.PUT)
    public ResponseEntity<Task> assignTaskToUser(@PathVariable String taskId, @RequestBody User user) {
        Task task = taskService.assignTaskToUser(taskId, user);
        return new ResponseEntity<>(task, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/v1/tasks", method = RequestMethod.POST)
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task taskCreated = taskService.createTask(task);
        return new ResponseEntity<>(taskCreated, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/v1/tasks/{taskId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeTask(@PathVariable String taskId) {
        taskService.removeTask(taskId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/tasks", method = RequestMethod.PUT)
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        Task taskUpdated = taskService.updateTask(task);
        return new ResponseEntity<>(taskUpdated, HttpStatus.ACCEPTED);
    }
}
