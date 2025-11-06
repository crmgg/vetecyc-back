package co.edu.uco.vetecyv.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("co.edu.uco.vetecyv")
public class VetecyvApplication {

	public static void main(String[] args) {
		SpringApplication.run(VetecyvApplication.class, args);
	}

}
