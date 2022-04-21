package codurance.academyfinalboy.backend.model.token;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TokenServiceShould {

  public static final long TEAM_ID = 5L;

  @Test
  void save_token() {
    TokenRepository mockedTokenRepository = mock(TokenRepository.class);
    TokenService tokenService = new TokenService(mockedTokenRepository);

    tokenService.generateToken(TEAM_ID);

    verify(mockedTokenRepository).save(new Token(TEAM_ID));
  }
}
