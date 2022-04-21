package codurance.academyfinalboy.backend.infrastructure.repositories.token;

import codurance.academyfinalboy.backend.BaseSpringTest;
import codurance.academyfinalboy.backend.model.token.Token;
import codurance.academyfinalboy.backend.model.token.TokenRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class TokenRepositoryShould extends BaseSpringTest {
  @Autowired TokenRepository repository;

  @Test
  void save_token() {
    Token token = new Token(3L, UUID.randomUUID(), LocalDateTime.now());
    Token savedToken = repository.save(token);
    token.setId(3L);

    assertThat(savedToken).isEqualTo(token);
  }
}
