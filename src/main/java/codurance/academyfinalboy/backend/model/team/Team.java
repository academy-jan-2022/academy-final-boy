package codurance.academyfinalboy.backend.model.team;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Table
public class Team {
  @Id private Long id;
  private String name;
  private String description;

  @MappedCollection(idColumn = "team_id")
  private List<Activity> activities;

  @MappedCollection(idColumn = "team_id")
  private Set<UserRef> members;

  @PersistenceConstructor
  public Team(
      Long id, String name, String description, List<Activity> activities, Set<UserRef> members) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.activities = activities;
    this.members = members;
  }

  public Team(String name, String description) {
    this.id = null;
    this.name = name;
    this.description = description;
    this.activities = new ArrayList<>();

    this.members = new HashSet<>();
  }

  public Team(String name, String description, long userId) {
    this(name, description);
    members.add(new UserRef(userId));
  }

  public void addActivity(Activity activity) {
    activities.add(activity);
  }
}
