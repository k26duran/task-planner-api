package edu.eci.TaskPlanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@SpringBootApplication
public class TaskPlannerApplication {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<String> getIndex() {
		return new ResponseEntity<>("Task Planner API", HttpStatus.OK);
	}

	public static void main(String[] args) {
		SpringApplication.run(TaskPlannerApplication.class, args);
	}

}
