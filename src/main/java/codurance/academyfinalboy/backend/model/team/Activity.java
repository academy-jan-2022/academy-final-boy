package codurance.academyfinalboy.backend.model.team;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
@NoArgsConstructor
public class Activity {
  private String name;

  @MappedCollection(idColumn = "team_id", keyColumn = "activity_key")
  private List<Group> groups;

  public Activity(String name, List<ActivityMember> members, int numberOfGroups) {
    if (members.size() < 3 || members.size() <= numberOfGroups) {
      throw new IllegalStateException("can't generate teams with current configuration");
    }

    Collections.shuffle(members);
    this.name = name;
    this.groups = partitionBasedOnSize(members, numberOfGroups);
  }

  static List<Group> partitionBasedOnSize(List<ActivityMember> inputList, int size) {
    final AtomicInteger counter = new AtomicInteger(0);
    return inputList.stream()
        .collect(Collectors.groupingBy(s -> counter.getAndIncrement() % size))
        .values()
        .stream()
        .map(Activity::toGroup)
        .toList();
  }

  private static Group toGroup(List<ActivityMember> grouping) {
    return new Group(grouping);
  }
}
