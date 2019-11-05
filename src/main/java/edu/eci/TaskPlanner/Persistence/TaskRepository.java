package edu.eci.TaskPlanner.Persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.eci.TaskPlanner.Model.Task;

public interface TaskRepository extends MongoRepository<Task, Integer> {

    Task findById(int id);
    
    List<Task> findByResponsibleEmail(String responsibleEmail);
    
    List<Task> findByResponsible(int id);

}