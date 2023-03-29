package br.com.dea.management.deamanagement;

import br.com.dea.management.deamanagement.student.domain.Student;
import br.com.dea.management.deamanagement.student.repository.StudentRepository;
import br.com.dea.management.deamanagement.user.domain.User;
import br.com.dea.management.deamanagement.user.repository.UserRepository;
import br.com.dea.management.deamanagement.user.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Dea Management", version = "1.0", description = "Dea Management API Description"),
		servers = {
				@Server(url = "http://localhost:8082${server.servlet.contextPath}", description = "Local environment URL"),
				@Server(url = "https://deamanagement.com.br${server.servlet.contextPath}", description = "Development environment URL")
		}
)
public class DeamanagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DeamanagementApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@Autowired
	private StudentRepository studentRepository;

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void run(String... args) throws Exception {
		//Deleting all Users
//		this.userRepository.deleteAll();

		//Creating some students
		for (int i = 0; i < 100; i++) {
			User u = new User();
			u.setEmail("email " + i);
			u.setName("name " + i);
			u.setLinkedin("linkedin " + i);
			u.setPassword("pwd " + i);

			Student student = new Student();
			student.setUniversity("UNI " + i);
			student.setGraduation("Grad " + i);
			student.setFinishDate(LocalDate.now());
			student.setUser(u);

			this.studentRepository.save(student);
		}

	}
}
