package codurance.academyfinalboy.backend.user;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("application_user")
public class User {
  @Id
  Long id;
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
