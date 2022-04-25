package codurance.academyfinalboy.backend.model.team;

import java.util.List;

public class Activity {
  private String name;
  private List<List<ActivityMember>> groups;

  public Activity(String name, List<ActivityMember> members, int numberOfGroups) {
    this.name = name;
  }

  public List<List<ActivityMember>> getGroups() {
    return groups;
  }

  public String getName() {
    return name;
  }
}
