package codurance.academyfinalboy.backend.user;

import lombok.Value;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Value
public class User {
  @Id Long id;
  UUID externalId;
  String fullName;
  String username;

  public User(UUID externalId, String fullName, String username) {
    this.id = null;
    this.externalId = externalId;
    this.fullName = fullName;
    this.username = username;
  }
}
