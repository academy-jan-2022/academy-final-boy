package codurance.academyfinalboy.backend.model.token;

import java.util.UUID;

public interface TokenRepository {
  Token save(Token token);

  void clear();

  Token getToken(UUID joinTokenID);
}
