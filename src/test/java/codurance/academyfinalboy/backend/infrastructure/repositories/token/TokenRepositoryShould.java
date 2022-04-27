package codurance.academyfinalboy.backend.infrastructure.repositories.token;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import codurance.academyfinalboy.backend.BaseSpringTest;
import codurance.academyfinalboy.backend.model.token.Token;
import codurance.academyfinalboy.backend.model.token.TokenRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class TokenRepositoryShould extends BaseSpringTest {
  @Autowired TokenRepository repository;

  @Test
  void save_token() {
    Token token = new Token(3L, UUID.randomUUID(), LocalDateTime.now());
    Token savedToken = repository.save(token);
    token.setId(3L);

    assertThat(savedToken).isEqualTo(token);
  }

    @Test void
    find_token_by_join_id() {
      Token token = new Token(3L, UUID.randomUUID(), LocalDateTime.now());
      Token savedToken = repository.save(token);
      token.setId(3L);

      Optional<Token> foundToken = repository.getToken(savedToken.getJoinId());

      assertEquals(foundToken, savedToken);
    }

  @AfterEach
  void tearDown() {
    repository.clear();
  }
}
