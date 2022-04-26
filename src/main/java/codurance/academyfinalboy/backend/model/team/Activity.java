package codurance.academyfinalboy.backend.model.team;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Data
public class Activity {
  @Id private Long id;
  private String name;

  @MappedCollection(idColumn = "activity_id")
  private Set<Group> groups;

  public Activity(String name, List<ActivityMember> members, int numberOfGroups) {
    if (members.size() < 3 || members.size() <= numberOfGroups) {
      throw new IllegalStateException("can't generate teams with current configuration");
    }

    Collections.shuffle(members);
    this.name = name;
    this.groups = partitionBasedOnSize(members, numberOfGroups);
  }

  static Set<Group> partitionBasedOnSize(List<ActivityMember> inputList, int size) {
    final AtomicInteger counter = new AtomicInteger(0);
    return inputList.stream()
        .collect(Collectors.groupingBy(s -> counter.getAndIncrement() % size))
        .values()
        .stream()
        .map(Activity::toGroup)
        .collect(Collectors.toSet());
  }

  private static Group toGroup(List<ActivityMember> grouping) {
    return new Group(new HashSet<>(grouping));
  }

  public Set<Set<ActivityMember>> getGroups() {
    return groups.stream().map(Group::getGrouping).collect(Collectors.toSet());
  }
}
