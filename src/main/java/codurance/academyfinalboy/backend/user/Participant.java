package codurance.academyfinalboy.backend.user;

import lombok.Data;
import org.springframework.data.annotation.Id;
import java.util.UUID;

@Data

public class Participant {
  @Id
  Long id;
  UUID externalId;
  String fullName;
  String username;

  public Participant(UUID externalId, String fullName, String username) {
    this.id = null;
    this.externalId = externalId;
    this.fullName = fullName;
    this.username = username;
  }
}
