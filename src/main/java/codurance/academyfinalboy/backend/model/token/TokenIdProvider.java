package codurance.academyfinalboy.backend.model.token;

import java.util.UUID;

public class TokenIdProvider {
  public UUID random() {
    return UUID.randomUUID();
  }
}
