package codurance.academyfinalboy.backend.model.team;

import lombok.Data;

import java.util.List;

@Data
public class ActivityView {

  private String name;
  private List<List<Member>> groups;

  public ActivityView(Activity activity) {
    this.name = activity.getName();
    this.groups =
        activity.getGroups().stream()
            .map(
                group ->
                    group.getGrouping().stream()
                        .map(group2 -> new Member(group2.getName()))
                        .toList())
            .toList();
  }

  record Member(String name) {}
}
