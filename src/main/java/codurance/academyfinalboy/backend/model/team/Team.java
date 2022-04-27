package codurance.academyfinalboy.backend.model.team;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
@NoArgsConstructor
public class Team {
  @Id private Long id;
  private String name;
  private String description;

  @MappedCollection(idColumn = "team_id")
  private Set<UserRef> members;

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

  public void addMember(long userId) {

  }
}
