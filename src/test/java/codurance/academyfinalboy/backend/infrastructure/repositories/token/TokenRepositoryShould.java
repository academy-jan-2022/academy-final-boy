package codurance.academyfinalboy.backend.infrastructure.repositories.token;

import codurance.academyfinalboy.backend.BaseSpringTest;
import codurance.academyfinalboy.backend.model.token.TokenIdProvider;
import codurance.academyfinalboy.backend.model.token.TokenRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TokenRepositoryShould extends BaseSpringTest {
  @Autowired TokenRepository repository;

  @Test
  void save_token() {

  }
}
