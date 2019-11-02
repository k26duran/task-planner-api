package edu.eci.TaskPlanner.Persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.eci.TaskPlanner.Model.User;

public interface UserRepository extends MongoRepository<User, Integer> {

    User findById(int id);

    User findByEmail(String email);

    User findByUsername(String username);
}
