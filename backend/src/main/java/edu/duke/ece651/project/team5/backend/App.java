package edu.duke.ece651.project.team5.backend;

import edu.duke.ece651.project.team5.backend.repository.StudentDao;
import edu.duke.ece651.project.team5.shared.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
@EntityScan({"edu.duke.ece651.project.team5.shared.model", "edu.duke.ece651.project.team5.backend.model"})


public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
