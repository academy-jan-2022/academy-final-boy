package codurance.academyfinalboy.backend.model.token;

import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class TokenIdProvider {
  public UUID random() {
    return UUID.randomUUID();
  }
}
