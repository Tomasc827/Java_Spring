package lt.techin.Online_Course_Platform.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class WebUtils {

  public static URI createLocation(String path, long id) {
    return ServletUriComponentsBuilder.fromCurrentRequest()
            .path(path)
            .buildAndExpand(id)
            .toUri();
  }

}
