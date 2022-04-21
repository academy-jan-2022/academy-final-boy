package codurance.academyfinalboy.backend.model.token;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Token {
  private final long teamId;
  private UUID joinId;
  @Id private long id;

  public Token(long teamId, UUID joinId, LocalDateTime tokenExpiryDate) {
    this.teamId = teamId;
    this.joinId = joinId;
  }
}
