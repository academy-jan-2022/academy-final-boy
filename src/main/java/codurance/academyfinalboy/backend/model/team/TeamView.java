package codurance.academyfinalboy.backend.model.team;

import codurance.academyfinalboy.backend.model.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class TeamView {

  String id;
  String name;
  String description;
  List<Member> teamMembers;

  public TeamView(Team team, List<User> users) {
    this.id = team.getId().toString();
    this.name = team.getName();
    this.description = team.getDescription();
    this.teamMembers = createMembers(users);
  }

  private List<Member> createMembers(List<User> users) {
    return users
            .stream()
            .map(user -> new Member(user.getId(), user.getFullName()))
            .toList();
  }

  public record Member(Long id, String name){};
}
