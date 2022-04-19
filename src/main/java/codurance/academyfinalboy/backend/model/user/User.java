package codurance.academyfinalboy.backend.model.user;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("application_user")
public class User {
  @Id Long id;
  String externalId;
  String fullName;
  String username;

  @MappedCollection(idColumn = "user_id")
  Set<TeamRef> teams = new HashSet<>();

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

  public Set<TeamRef> getTeams() {
    return teams;
  }

  public void addTeam(Long teamId) {
    teams.add(new TeamRef(teamId));
  }
}
