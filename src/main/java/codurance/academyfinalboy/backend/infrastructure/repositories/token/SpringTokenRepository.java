package codurance.academyfinalboy.backend.infrastructure.repositories.token;

import codurance.academyfinalboy.backend.model.token.Token;
import codurance.academyfinalboy.backend.model.token.TokenRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class SpringTokenRepository implements TokenRepository {
  private final SpringDataJdbcTokenRepository repository;

  public SpringTokenRepository(SpringDataJdbcTokenRepository repository) {
    this.repository = repository;
  }

  @Override
  public Token save(Token token) {
    return repository.save(token);
  }

  @Override
  public void clear() {
    repository.deleteAll();
  }

  @Override
  public Optional<Token> findByJoinId(UUID joinTokenID) {
    return repository.findByJoinId(joinTokenID);
  }
}
