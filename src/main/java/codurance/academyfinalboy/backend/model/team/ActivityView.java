package codurance.academyfinalboy.backend.model.team;

import lombok.Data;

import java.util.List;

@Data
public class ActivityView {

  private String name;
  private List<List<Member>> groups;

  public ActivityView(Activity activity) {
    this.name = activity.getName();
    this.groups = activity.getGroups().stream().map(this::toListOfMembers).toList();
  }

  private List<Member> toListOfMembers(Group group) {
    return group.getGrouping().stream().map(ActivityView::toMember).toList();
  }

  private static Member toMember(ActivityMember activityMember) {
    return new Member(activityMember.getName());
  }

  record Member(String name) {}
}
