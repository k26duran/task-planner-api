package edu.eci.TaskPlanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.eci.TaskPlanner.Services.TaskService;
import edu.eci.TaskPlanner.Services.UserService;

@Controller
@SpringBootApplication
public class TaskPlannerApplication implements CommandLineRunner{

	@Autowired
	@Qualifier("taskServiceMongo")
	TaskService iTaskService;

	@Autowired
	@Qualifier("userServiceMongo")
	UserService iUserService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<String> getIndex() {
		return new ResponseEntity<>("Task Planner API", HttpStatus.OK);
	}

	public static void main(String[] args) {
		SpringApplication.run(TaskPlannerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
		MongoOperations mongoOperation = (MongoOperations) applicationContext.getBean("mongoTemplate");
		
		
	}

}
