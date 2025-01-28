package lt.techin.h2_attempt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootTest
class H2AttemptApplicationTests {

	@Test
	void contextLoads() {
	}
	@GetMapping("/test")
	public String test() {
		return "Controller is working!";
	}

}
