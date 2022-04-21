package codurance.academyfinalboy.backend.model.token;

import java.util.UUID;

public class TokenService {
  private final TokenRepository tokenRepository;
  private final TokenIdProvider tokenIdProvider;

  public TokenService(TokenRepository tokenRepository, TokenIdProvider tokenIdProvider, TimeProvider mockedTimeProvider) {
    this.tokenRepository = tokenRepository;
    this.tokenIdProvider = tokenIdProvider;
  }

  public UUID generateToken(long teamId) {
    tokenRepository.save(new Token(teamId, tokenIdProvider.random()));
    return tokenIdProvider.random();
  }
}
