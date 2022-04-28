package codurance.academyfinalboy.backend.model.token;

import java.util.Optional;
import java.util.UUID;

public interface TokenRepository {
  Token save(Token token);

  void clear();

  Optional<Token> findByJoinId(UUID joinTokenID);
}
