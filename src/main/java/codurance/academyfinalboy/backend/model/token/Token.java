package codurance.academyfinalboy.backend.model.token;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Token {
  private final Long teamId;
  private UUID joinId;
  private LocalDateTime expiryDate;
  @Id private long id;

  public Token(long teamId, UUID joinId, LocalDateTime expiryDate) {
    this.teamId = teamId;
    this.joinId = joinId;
    this.expiryDate = expiryDate;
  }
}
