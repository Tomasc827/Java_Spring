package lt.techin1.__better;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ApplicationContext applicationContext) {
		return args -> {
			System.out.println("\n\nLet's not inspect the beans\n\n");

			String[] beanNames = applicationContext.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			Arrays.stream(beanNames).forEach(System.out::println);
		};
	}

}
