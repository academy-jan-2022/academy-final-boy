package codurance.academyfinalboy.backend.model.team;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
public class Team {
  @Id Long id;
  String name;
  String description;
  List<Long> members;

  public Team(String name, String description) {
    this.id = null;
    this.name = name;
    this.description = description;
    this.members = new ArrayList<>();
  }

  public Team(String name, String description, long userId) {
    this(name, description);
    members.add(userId);
  }
}
