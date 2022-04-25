package codurance.academyfinalboy.backend.model.token;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;

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
