package codurance.academyfinalboy.backend.user;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
public class User {
  @Id private final Long id;
  private final UUID externalId;
  private final String fullName;
  private final String username;

  public User(UUID externalId, String fullName, String username) {
    this.id = null;
    this.externalId = externalId;
    this.fullName = fullName;
    this.username = username;
  }
}
