package codurance.academyfinalboy.backend.model.team;

import codurance.academyfinalboy.backend.model.user.User;
import java.util.List;
import lombok.Data;

@Data
public class TeamView {

  String id;
  String name;
  String description;
  List<Member> members;
  List<ActivityView> activities;

  public TeamView(Team team, List<User> users) {
    this.id = team.getId().toString();
    this.name = team.getName();
    this.description = team.getDescription();
    this.activities = team.getActivities().stream().map(ActivityView::new).toList();
    this.members = createMembers(users);
  }

  private List<Member> createMembers(List<User> users) {
    return users.stream().map(user -> new Member(user.getId(), user.getFullName())).toList();
  }

  public record Member(Long id, String fullName) {}
}
