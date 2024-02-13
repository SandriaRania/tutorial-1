package id.ac.ui.cs.advprog.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "id.ac.ui.cs.advprog.eshop.controller, " + "id.ac.ui.cs.advprog.eshop.model, "
		+ "id.ac.ui.cs.advprog.eshop.repository, " + "id.ac.ui.cs.advprog.eshop.service")
public class EshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopApplication.class, args);
	}

}
