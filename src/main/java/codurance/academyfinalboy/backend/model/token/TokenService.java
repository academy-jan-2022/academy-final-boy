package codurance.academyfinalboy.backend.model.token;

import java.util.UUID;

public class TokenService {
  private final TokenRepository tokenRepository;

  public TokenService(TokenRepository tokenRepository) {
    this.tokenRepository = tokenRepository;
  }

  public UUID generateToken(long teamId) {
    tokenRepository.save(new Token(teamId));
    return null;
  }
}
