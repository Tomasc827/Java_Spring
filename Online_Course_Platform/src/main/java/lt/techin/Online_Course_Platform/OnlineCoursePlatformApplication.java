package lt.techin.Online_Course_Platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class OnlineCoursePlatformApplication {

  public static void main(String[] args) {
    SpringApplication.run(OnlineCoursePlatformApplication.class, args);
  }

}
