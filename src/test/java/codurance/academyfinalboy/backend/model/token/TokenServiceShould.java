package codurance.academyfinalboy.backend.model.token;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TokenServiceShould {

  public static final long TEAM_ID = 5L;
  public static final UUID TOKEN_ID = UUID.randomUUID();
  private TokenRepository mockedTokenRepository;
  private TokenService tokenService;
  private TokenIdProvider mockedTokenIdProvider;

  @BeforeEach
  void setUp() {
    mockedTokenRepository = mock(TokenRepository.class);
    mockedTokenIdProvider = mock(TokenIdProvider.class);
    tokenService = new TokenService(mockedTokenRepository, mockedTokenIdProvider);
  }

  @Test
  void save_token() {
    tokenService.generateToken(TEAM_ID);

    verify(mockedTokenRepository).save(new Token(TEAM_ID));
  }

  @Test
  void generate_token() {
    when(mockedTokenIdProvider.random()).thenReturn(TOKEN_ID);
    UUID generatedToken = tokenService.generateToken(TEAM_ID);

    assertThat(generatedToken).isEqualTo(TOKEN_ID);
  }
}
