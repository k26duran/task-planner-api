package edu.eci.TaskPlanner.Controllers;

import edu.eci.TaskPlanner.Model.Task;
import edu.eci.TaskPlanner.Model.User;
import edu.eci.TaskPlanner.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mongodb.client.gridfs.model.GridFSFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/taskPlanner")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    GridFsTemplate gridFsTemplate;

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
    public ResponseEntity<Task> putTaskToUser(@PathVariable String taskId, @RequestBody User user) {
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
    //para los archivos
    @RequestMapping(value = "/v1/files/{filename}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> getFileByName(@PathVariable String filename) throws IOException {
        GridFSFile file = gridFsTemplate.findOne(new Query().addCriteria(Criteria.where("filename").is(filename)));
        if (file == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            GridFsResource resource = gridFsTemplate.getResource(file.getFilename());
            return ResponseEntity.ok().contentType(MediaType.valueOf(resource.getContentType())).body(new InputStreamResource(resource.getInputStream()));
        }
    }
    @RequestMapping(value = "/v1/files", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        String nameF = file.getOriginalFilename();
        gridFsTemplate.store(file.getInputStream(), nameF, file.getContentType());
        return nameF;
    }
}
