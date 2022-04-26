package codurance.academyfinalboy.backend.model.team;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Activity {
  private String name;
  private List<List<ActivityMember>> groups;

  public Activity(String name, List<ActivityMember> members, int numberOfGroups) {
    if (members.size() < 3 || members.size() <= numberOfGroups) {
      throw new IllegalStateException("can't generate teams with current configuration");
    }

    this.name = name;
    this.groups = partitionBasedOnSize(members, numberOfGroups);
  }

  static List<List<ActivityMember>> partitionBasedOnSize(List<ActivityMember> inputList, int size) {
    final AtomicInteger counter = new AtomicInteger(0);
    return inputList.stream()
        .collect(Collectors.groupingBy(s -> counter.getAndIncrement() % size))
        .values()
        .stream()
        .toList();
  }

  public List<List<ActivityMember>> getGroups() {
    return groups;
  }

  public String getName() {
    return name;
  }
}
