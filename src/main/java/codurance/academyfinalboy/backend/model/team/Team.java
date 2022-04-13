package codurance.academyfinalboy.backend.model.team;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import javax.websocket.OnOpen;
import java.util.HashSet;
import java.util.Set;

@Data
@Table
@NoArgsConstructor
public class Team {
  @Id Long id;
  String name;
  String description;

  @MappedCollection(idColumn = "team_id")
  Set<UserRef> members;

  public Team(String name, String description) {
    this.id = null;
    this.name = name;
    this.description = description;

    this.members = new HashSet<>();
  }

  public Team(String name, String description, long userId) {
    this(name, description);
    members.add(new UserRef(userId));
  }
}
