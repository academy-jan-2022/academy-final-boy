package codurance.academyfinalboy.backend.model.team;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;

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
}
