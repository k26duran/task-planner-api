package edu.eci.TaskPlanner;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mongodb.client.gridfs.model.GridFSFile;

import edu.eci.TaskPlanner.Config.JwtFilter;
import edu.eci.TaskPlanner.Model.Status;
import edu.eci.TaskPlanner.Model.Task;
import edu.eci.TaskPlanner.Model.User;
import edu.eci.TaskPlanner.Persistence.TaskRepository;
import edu.eci.TaskPlanner.Persistence.UserRepository;

@Controller
@SpringBootApplication
public class TaskPlannerApplication implements CommandLineRunner{

	@Autowired
    GridFsTemplate gridFsTemplate;

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	UserRepository userRepository;
	
	
	@Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns( "/taskPlanner/v1/tasks/*","/taskPlanner/v1/users/*","/taskPlanner/v1/files/*");
        return registrationBean;
    }
	
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
		/*
		userRepository.deleteAll();
        User user1 = new User(1, "Karen Duran", "k26duran", "k26duran@mail.com", "99999", null);
        userRepository.save(user1);
        User user2 = new User(2, "Sebastian Choachi", "sebas7425", "sebas@mail.com", "74356", null);
        userRepository.save(user2);
        userRepository.save(new User(3, "Maximiliano Vivas", "maxin1", "maxi25@mail.com", "23456", null));
		User user4 = new User(4, "Pollo Alvarez", "pollito05", "pollito05@mail.com", "12345", null);
        userRepository.save(user4);
        userRepository.save(new User(5, "Federico Martinez", "Federico98", "fedeMar@mail.com", "12345", null));
        userRepository.save(new User(6, "Lina Martinez", "lina44", "linaMartinez@mail.com", "000000", null));
        userRepository.save(new User(7, "Juan Velez", "juanVelez", "juanV_09@mail.com", "777777", null));
        userRepository.save(new User(8, "Margarita Agudelo", "Margi08", "margaretAgu@mail.com", "123123", null));
        userRepository.save(new User(9, "Fabio Duarte", "FadoDu", "fadoDuarte@mail.com", "123321", null));
        userRepository.save(new User(10, "Freya Ortiz", "freyaMaria", "freyaOrtiz21@mail.com", "11111", null));
        taskRepository.deleteAll();
        taskRepository.save(new Task(1, "React App 1", "Create a new task 1 asasdadadjjadljdlaja", "2019-08-29", Status.COMPLETE, user1,1,null));
		taskRepository.save(new Task(2, "React App 2", "Create a new task 2", "2019-09-26", Status.COMPLETE, user2,1,null));
		taskRepository.save(new Task(3, "React App 3", "Create a new task 3", "2019-08-23", Status.COMPLETE, user1,1,null));
		taskRepository.save(new Task(4, "React App 4", "Create a new task 4", "2019-09-09", Status.COMPLETE, user2,1,null));
		taskRepository.save(new Task(5, "React App 5", "Create a new task 5", "2019-08-20", Status.COMPLETE, user1,1,null));
		taskRepository.save(new Task(6, "React App 6", "Create a new task 6", "2019-09-25", Status.IN_PROGRESS, null,1,null));
		taskRepository.save(new Task(7, "React App 7", "Create a new task 7", "2019-08-25", Status.IN_PROGRESS, null,1,null));
		taskRepository.save(new Task(8, "React App 8", "Create a new task 8", "2019-10-25", Status.IN_PROGRESS, null,1,null));
		taskRepository.save(new Task(9, "React App 9", "Create a new task 9", "2019-11-25", Status.IN_PROGRESS, user1,1,null));
		taskRepository.save(new Task(10, "React App 10", "Create a new task 10", "2019-10-25", Status.IN_PROGRESS, user2,1,null));
		taskRepository.save(new Task(11, "React App 11", "Create a new task 11", "2019-11-27", Status.READY, user1,1,null));
		taskRepository.save(new Task(12, "React App 12", "Create a new task 12", "2019-10-26", Status.READY, user4,1,null));
		taskRepository.save(new Task(13, "React App 13", "Create a new task 13", "2019-10-25", Status.READY, user2,1,null));
		taskRepository.save(new Task(14, "React App 14", "Create a new task 14", "2019-10-23", Status.READY, user1,1,null));
		taskRepository.save(new Task(15, "React App 15", "Create a new task 15", "2019-11-22", Status.READY, user1,1,null));
		taskRepository.save(new Task(16, "React App 16", "Create a new task 16", "2019-12-25", Status.COMPLETE, user4,1,null));
		taskRepository.save(new Task(17, "React App 17", "Create a new task 17", "2019-12-02", Status.COMPLETE, null,1,null));
		taskRepository.save(new Task(18, "React App 18", "Create a new task 18", "2019-12-09", Status.COMPLETE, user2,1,null));
		taskRepository.save(new Task(19, "React App 19", "Create a new task 19", "2019-12-14", Status.COMPLETE, user4,1,null));
		taskRepository.save(new Task(20, "React App 20", "Create a new task 20", "2019-12-12", Status.COMPLETE, null,1,null));
		taskRepository.save(new Task(21, "React App 21", "Create a new task 21", "2019-12-01", Status.READY, user4,1,null));
		taskRepository.save(new Task(22, "React App 22", "Create a new task 22", "2019-12-01", Status.READY, null,2,null));
		taskRepository.save(new Task(23, "React App 23", "Create a new task 23", "2019-12-11", Status.READY, user2,2,null));
		taskRepository.save(new Task(24, "React App 24", "Create a new task 24", "2019-12-10", Status.IN_PROGRESS, user1,2,null));
		taskRepository.save(new Task(25, "React App 25", "Create a new task 25", "2019-12-22", Status.IN_PROGRESS, null,2,null));
		
		Query query = new Query();
		//dueDate expired.
        query.addCriteria(Criteria.where("dueDate").lt("2019-11-23"));
        List<Task> tareasExpiradas = mongoOperation.find(query, Task.class);
        System.out.println("***Tareas Expiradas:***");
        for (Task te: tareasExpiradas) {
            System.out.println(te);
        }
        //priority greater equal to 5 (Status Ready)
        query = new Query();
        query.addCriteria(Criteria.where("responsible").is(user1).and("status").is("READY"));
        List<Task> readyT = mongoOperation.find(query, Task.class);
        System.out.println("***Tareas con responsible y estado="READY" :***");
        for (Task t: readyT) {
            System.out.println(t);
        }
        //users that have assigned more than 2 Todos.
        query = new Query();
        query.addCriteria(Criteria.where("responsible").is("k26duran@mail.com").and("status").is("COMPLETE"));
        List<User> users = mongoOperation.find(query, User.class);
        System.out.println("***Usuarios con más de 2 tareas:***");
        for (User u: users) {
            System.out.println(u);
        }
        //Todolist that contains the description with a length greater than 30 characters.
        query = new Query();
        query.addCriteria(Criteria.where("description").size(30));
        List<Task> tasks = mongoOperation.find(query, Task.class);
        System.out.println("***Tareas con descripción extensa: ***");
        for (Task t: tasks) {
            System.out.println(t);
        }
	
	*/
		gridFsTemplate.delete(new Query().addCriteria(Criteria.where("filename").is("tiger.jpg")));
        URL url = new URL("https://t2.ev.ltmcdn.com/es/posts/5/7/3/los_animales_en_mayor_peligro_de_extincion_375_600.jpg");
        gridFsTemplate.store(url.openStream(), "tiger.jpg",  "image/jpg");
        System.out.println("****Image****");
        GridFSFile file = gridFsTemplate.findOne(new Query().addCriteria(Criteria.where("filename").is("tiger.jpg")));
        System.out.println("File Exists: " + (file!=null));
		
	}

}
