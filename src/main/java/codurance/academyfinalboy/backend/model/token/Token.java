package codurance.academyfinalboy.backend.model.token;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Token {
  private final long teamId;
  private UUID tokenId;

  public Token(long teamId, UUID tokenId, LocalDateTime tokenExpiryDate) {
    this.teamId = teamId;
    this.tokenId = tokenId;
  }
}
