package edu.eci.TaskPlanner;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.eci.TaskPlanner.Model.Status;
import edu.eci.TaskPlanner.Model.Task;
import edu.eci.TaskPlanner.Model.User;
import edu.eci.TaskPlanner.Persistence.TaskRepository;
import edu.eci.TaskPlanner.Persistence.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskPlannerApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void findAllUsers() {
		userRepository.deleteAll();
		userRepository.save(new User(1,"Karen Duran","k26duran","k26duran@mail.com","111111",null));
		userRepository.save(new User(2,"Sebastian Choachi","sebas7428","sebas@mail.com","111111",null));
		int c = 0;
		System.out.println("Results of findAll");
		for (User u : userRepository.findAll()) {
			System.out.println(u);
			c++;
		}
		System.out.println();
		assertTrue(c == 2);
	}

	@Test
	public void findUsersByEmail() {
		userRepository.deleteAll();
		userRepository.save(new User(1,"Karen Duran","k26duran","k26duran@mail.com","111111",null));
		userRepository.save(new User(2,"Sebastian Choachi","sebas7428","sebas@mail.com","111111",null));
		
		System.out.println("Results of findByEmail");
		String email = "k26duran@mail.com";
		User user = userRepository.findByEmail(email);
		System.out.println(user);
		assertTrue(email.equals(user.getEmail()));
		String email2 = "sebas@mail.com";
		User user2 = userRepository.findByEmail(email2);
		System.out.println(user2);
		assertTrue(email2.equals(user2.getEmail()));
	}

	@Test
	public void findUserByUsername() {
		userRepository.deleteAll();
		userRepository.save(new User(1,"Karen Duran","k26duran","k26duran@mail.com","111111",null));
		userRepository.save(new User(2,"Sebastian Choachi","sebas7428","sebas@mail.com","111111",null));
		
		System.out.println("Results:");
		String username = "k26duran";
		User user = userRepository.findByUsername(username);
		System.out.println("User found: "+user);
		assertTrue(username.equals(user.getUsername()));
		String username2 = "sebas7428";
		User user2 = userRepository.findByUsername(username2);
		System.out.println("User found: "+user2);
		assertTrue(username2.equals(user2.getUsername()));
	}

	@Test
	public void findTasks() {
		taskRepository.deleteAll();
		taskRepository.save(new Task(1,"Example 1","Example of a task description","2019-10-25", Status.COMPLETE,null,1,null));
		taskRepository.save(new Task(2,"Example 2","Example of a task description","2019-10-25", Status.READY,null,1,null));
		int c = 0;
		System.out.println("Tasks found:");
		for (Task task : taskRepository.findAll()) {
			System.out.println(task);
			c++;
		}
		assertTrue(c == 2);
	}

	@Test
	public void itShouldFindTasksByResponsibleEmail() {
		userRepository.deleteAll();
		User user = new User(1,"Karen Duran","k26duran","k26duran@mail.com","111111",null);
		User user2 = new User(2,"Sebastian Choachi","sebas7428","sebas@mail.com","111111",null);
		userRepository.save(user);
		userRepository.save(user2);
		taskRepository.deleteAll();
		taskRepository.save(new Task(1,"Example 1","Example of a task description","2019-10-25", Status.COMPLETE,user,1,null));
		taskRepository.save(new Task(2,"Example 2","Example of a task description","2019-10-25", Status.READY,user,1,null));
		taskRepository.save(new Task(3,"Example 1","Example of a task description","2019-10-25", Status.COMPLETE,user2,1,null));
		taskRepository.save(new Task(4,"Example 2","Example of a task description","2019-10-25", Status.READY,user2,1,null));
		
		int c = 0;
		System.out.println("Tasks found by email of responsible");
		for (Task task : taskRepository.findByResponsibleEmail("k26duran@mail.com")) {
			System.out.println(task);
			c++;
		}
		assertTrue(c == 2);
		int p = 0;
		System.out.println("Tasks found by email of responsible");
		for (Task task : taskRepository.findByResponsibleEmail("sebas@mail.com")) {
			System.out.println(task);
			p++;
		}
		assertTrue(p == 2);
	}
}
