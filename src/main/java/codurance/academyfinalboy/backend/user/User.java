package codurance.academyfinalboy.backend.user;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Arrays;
import java.util.UUID;

@Data
@Table("application_user")
public class User {
  @Id Long id;
  UUID externalId;
  String fullName;
  String username;

  public User(UUID externalId, String fullName) {
    this.id = null;
    this.externalId = externalId;
    this.fullName = fullName;
    this.username = createUsername(fullName);
  }

  private static Character getFirstLetter(String name) {
    return name.charAt(0);
  }

  private static String createUsername(String fullName) {
    StringBuilder username = new StringBuilder();
    String[] names = fullName.split(" ");
    username.append(names[0]);

    Arrays.stream(names).skip(1).map(User::getFirstLetter).forEach(username::append);

    return username.toString();
  }
}
