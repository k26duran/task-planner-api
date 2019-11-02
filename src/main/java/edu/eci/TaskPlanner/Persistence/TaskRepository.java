package edu.eci.TaskPlanner.Persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.eci.TaskPlanner.Model.Task;

public interface TaskRepository extends MongoRepository<Task, Integer> {

    Task findById(int id);

    List<Task> findByResponsible_Email(String responsibleEmail);
}