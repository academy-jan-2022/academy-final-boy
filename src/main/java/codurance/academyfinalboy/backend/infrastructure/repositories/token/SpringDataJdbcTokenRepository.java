package codurance.academyfinalboy.backend.infrastructure.repositories.token;

import codurance.academyfinalboy.backend.model.token.Token;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface SpringDataJdbcTokenRepository extends CrudRepository<Token, Long> {
  Optional<Token> findByJoinId(UUID joinTokenID);
}
