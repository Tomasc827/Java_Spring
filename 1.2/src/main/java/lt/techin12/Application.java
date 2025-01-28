package lt.techin12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @GetMapping("/hello")
  public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    return String.format("Hello ⊂_ヽ\n" +
            "　 ＼＼\n" +
            "　　 ＼( ͡° ͜ʖ ͡°)\n" +
            "　　　 >　⌒ヽ\n" +
            "　　　/ 　 へ＼\n" +
            "　　 /　　/　＼＼\n" +
            "　　 ﾚ　ノ　　 ヽ_つ\n" +
            "　　/　/\n" +
            "　 /　/|\n" +
            "　(　(ヽ\n" +
            "　|　|、＼\n" +
            "　| 丿 ＼ ⌒)\n" +
            "　| |　　) /\n" +
            "ノ )　　Lﾉ\n" +
            "(_／ %s!", name);
  }
}