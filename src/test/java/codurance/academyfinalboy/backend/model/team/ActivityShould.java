package codurance.academyfinalboy.backend.model.team;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static codurance.academyfinalboy.backend.model.team.Activity.partitionBasedOnSize;
import static org.assertj.core.api.Assertions.assertThat;

class ActivityShould {

  @Test
  void throw_exception_on_team_smaller_than_3() {
    List<ActivityMember> members = generateActivityMembersBy(2);
    int numberOfGroups = 2;

    var actualException =
        Assertions.assertThrows(
            IllegalStateException.class, () -> new Activity("name", members, numberOfGroups));

    var exceptionMessage = "can't generate teams with current configuration";
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
  void splits_evenly_team_members_into_specified_number_of_groups() {
    var activityMembers = generateActivityMembersBy(4);
    var numberOfGroups = 2;
    var activity = new Activity("ECA", activityMembers, numberOfGroups);

    assertThat(activity.getGroups().get(0)).hasSize(2);
    assertThat(activity.getGroups().get(1)).hasSize(2);
  }

  @Test
  void splits_unevenly_team_members_into_specified_number_of_groups() {
    var activityMembers = generateActivityMembersBy(5);
    var numberOfGroups = 3;
    var activity = new Activity("ECA", activityMembers, numberOfGroups);

    assertThat(activity.getGroups().get(0)).hasSize(2);
    assertThat(activity.getGroups().get(1)).hasSize(2);
    assertThat(activity.getGroups().get(2)).hasSize(1);
  }

  @Test
  void throw_exception_on_number_of_teams_equal_to_members() {
    var activityMembers = generateActivityMembersBy(3);
    var numberOfGroups = 3;
    var actualException =
        Assertions.assertThrows(
            IllegalStateException.class,
            () -> new Activity("ECA", activityMembers, numberOfGroups));

    var exceptionMessage = "can't generate teams with current configuration";
    assertThat(actualException.getMessage()).isEqualTo(exceptionMessage);
  }

  @Test
  void throw_exception_on_number_of_teams_greater_to_members() {
    var activityMembers = generateActivityMembersBy(3);
    var numberOfGroups = 4;
    var actualException =
        Assertions.assertThrows(
            IllegalStateException.class,
            () -> new Activity("ECA", activityMembers, numberOfGroups));

    var exceptionMessage = "can't generate teams with current configuration";
    assertThat(actualException.getMessage()).isEqualTo(exceptionMessage);
  }

  @Test
  void randomize_the_groupings() {
    var activityMembers = generateActivityMembersBy(30);
    var numberOfGroups = 5;

    List<List<ActivityMember>> groups = partitionBasedOnSize(activityMembers, numberOfGroups);

    Activity activity = new Activity("ECA", activityMembers, numberOfGroups);

    assertThat(activity.getGroups()).isNotEqualTo(groups);
  }

  List<ActivityMember> generateActivityMembersBy(int numberOfMembers) {
    return new ArrayList<>(
        IntStream.range(0, numberOfMembers)
            .mapToObj(index -> new ActivityMember(String.valueOf(index)))
            .toList());
  }
}
