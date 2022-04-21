package codurance.academyfinalboy.backend.model.token;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TokenServiceShould {

  public static final long TEAM_ID = 5L;
  public static final UUID TOKEN = UUID.randomUUID();
  private TokenRepository mockedTokenRepository;
  private TokenService tokenService;
  private TokenProvider mockedTokenProvider;

  @BeforeEach
  void setUp() {
    mockedTokenRepository = mock(TokenRepository.class);
    mockedTokenProvider = mock(TokenProvider.class);
    tokenService = new TokenService(mockedTokenRepository, mockedTokenProvider);
  }

  @Test
  void save_token() {
    tokenService.generateToken(TEAM_ID);

    verify(mockedTokenRepository).save(new Token(TEAM_ID));
  }

  @Test
  void generate_token() {
    when(mockedTokenProvider.random()).thenReturn(TOKEN);
    UUID generatedToken = tokenService.generateToken(TEAM_ID);

    assertThat(generatedToken).isEqualTo(TOKEN);
  }
}
