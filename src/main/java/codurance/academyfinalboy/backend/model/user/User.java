package codurance.academyfinalboy.backend.model.user;

import java.util.Arrays;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("application_user")
public class User {
  @Id Long id;
  String externalId;
  String fullName;
  String username;

  public User(String externalId, String fullName) {
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
