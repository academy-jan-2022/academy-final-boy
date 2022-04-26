package codurance.academyfinalboy.backend.model.team;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class ActivityShould {

  @Test
  void throw_exception_on_team_smaller_than_3() {
    List<ActivityMember> members = generateActivityMembersBy(2);
    int numberOfGroups = 2;

    var actualException =
        Assertions.assertThrows(
            IllegalStateException.class, () -> new Activity("name", members, numberOfGroups));

    var exceptionMessage = "You can't make groups with less than 3 team members";
    assertThat(actualException.getMessage()).isEqualTo(exceptionMessage);
  }

  @Test
  void returns_number_of_groups_specified() {
    var activityMembers = generateActivityMembersBy(4);
    var numberOfGroups = 2;
    var activity = new Activity("ECA", activityMembers, numberOfGroups);

    assertThat(activity.getGroups()).hasSize(numberOfGroups);
  }

  @Test
  void splits_team_members_into_specified_number_of_groups() {
    var activityMembers = generateActivityMembersBy(4);
    var numberOfGroups = 2;
    var activity = new Activity("ECA", activityMembers, numberOfGroups);

    assertThat(activity.getGroups().get(0)).hasSize(2);
    assertThat(activity.getGroups().get(1)).hasSize(2);
  }

  List<ActivityMember> generateActivityMembersBy(int numberOfMembers) {
    return IntStream.range(0, numberOfMembers)
        .mapToObj(index -> new ActivityMember(String.valueOf(index)))
        .toList();
  }
}
