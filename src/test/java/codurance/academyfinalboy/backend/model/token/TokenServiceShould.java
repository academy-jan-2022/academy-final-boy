package codurance.academyfinalboy.backend.model.token;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TokenServiceShould {

  public static final long TEAM_ID = 5L;
  public static final UUID TOKEN_ID = UUID.randomUUID();
  public static final LocalDateTime CURRENT_TIME = LocalDateTime.now();
  private TokenRepository mockedTokenRepository;
  private TokenService tokenService;
  private TokenIdProvider mockedTokenIdProvider;
  private TimeProvider mockedTimeProvider;

  @BeforeEach
  void setUp() {
    mockedTokenRepository = mock(TokenRepository.class);
    mockedTokenIdProvider = mock(TokenIdProvider.class);
    mockedTimeProvider = mock(TimeProvider.class);
    when(mockedTimeProvider.getCurrentTime()).thenReturn(CURRENT_TIME);
    tokenService =
        new TokenService(mockedTokenRepository, mockedTokenIdProvider, mockedTimeProvider);
  }

  @Test
  void call_time_provider_to_generate_expiry_date() {
    tokenService.generateToken(TEAM_ID);
    verify(mockedTimeProvider).getCurrentTime();
  }

  @Test
  void return_generated_token_id() {
    when(mockedTokenIdProvider.random()).thenReturn(TOKEN_ID, UUID.randomUUID());
    UUID generatedToken = tokenService.generateToken(TEAM_ID);

    assertThat(generatedToken).isEqualTo(TOKEN_ID);
  }

  @Test
  void save_token() {
    LocalDateTime tokenExpiryDate = CURRENT_TIME.plusMinutes(5);
    when(mockedTokenIdProvider.random()).thenReturn(TOKEN_ID);
    tokenService.generateToken(TEAM_ID);

    verify(mockedTokenRepository).save(new Token(TEAM_ID, TOKEN_ID, tokenExpiryDate));
  }

  @Test
  void get_the_token_from_the_repo() {
    UUID joinTokenID = UUID.randomUUID();
    tokenService.getToken(joinTokenID);

    verify(mockedTokenRepository).getToken(joinTokenID);
  }
}
