package codurance.academyfinalboy.backend.model.team;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Activity {
  private String name;
  private List<List<ActivityMember>> groups;

  public Activity(String name, List<ActivityMember> members, int numberOfGroups) {
    this.name = name;
    this.groups = new ArrayList<>();
    if(members.size() < 3) {
      throw new IllegalStateException("You can't make groups with less than 3 team members");
    }
    for(var i = 0; i < numberOfGroups; i++) {
      groups.add(new ArrayList<ActivityMember>());
    }
    while(members.size() != 0) {
      for (var group : groups) {
          group.add(members.get(0));
          members.remove(0);
      }
    }


  }

  public List<List<ActivityMember>> getGroups() {
    return groups;
  }

  public String getName() {
    return name;
  }
}
