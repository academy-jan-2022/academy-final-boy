package codurance.academyfinalboy.backend.model.token;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class TimeProvider {
  public LocalDateTime getCurrentTime() {
    return LocalDateTime.now();
  }
}
