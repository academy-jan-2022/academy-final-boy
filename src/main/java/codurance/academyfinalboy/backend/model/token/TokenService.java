package codurance.academyfinalboy.backend.model.token;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
  private final TokenRepository tokenRepository;
  private final TokenIdProvider tokenIdProvider;
  private final TimeProvider timeProvider;

  public TokenService(
      TokenRepository tokenRepository, TokenIdProvider tokenIdProvider, TimeProvider timeProvider) {
    this.tokenRepository = tokenRepository;
    this.tokenIdProvider = tokenIdProvider;
    this.timeProvider = timeProvider;
  }

  public UUID generateToken(long teamId) {
    var tokenExpiryDate = timeProvider.getCurrentTime().plusMinutes(5);
    var tokenId = tokenIdProvider.random();
    tokenRepository.save(new Token(teamId, tokenId, tokenExpiryDate));
    return tokenId;
  }

  public Token getToken(UUID joinTokenId) throws InvalidTokenException {
    Token token = tokenRepository.getToken(joinTokenId);

    if (isTokenExpired(token))  {
      throw new InvalidTokenException("Token is expired");
    }
    return token;
  }

  private boolean isTokenExpired(Token token) {
    LocalDateTime tokenExpiry = token.getExpiryDate();

    return tokenExpiry.compareTo(timeProvider.getCurrentTime()) < 0;
  }

}
