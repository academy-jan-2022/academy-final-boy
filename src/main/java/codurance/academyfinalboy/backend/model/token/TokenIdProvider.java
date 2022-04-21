package codurance.academyfinalboy.backend.model.token;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenIdProvider {
  public UUID random() {
    return UUID.randomUUID();
  }
}
