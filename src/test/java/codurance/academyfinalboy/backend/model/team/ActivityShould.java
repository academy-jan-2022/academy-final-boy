package codurance.academyfinalboy.backend.model.team;

import static codurance.academyfinalboy.backend.builders.ActivityBuilder.generateActivityMembersBy;
import static codurance.academyfinalboy.backend.model.team.Activity.partitionBasedOnSize;
import static org.assertj.core.api.Assertions.assertThat;

import codurance.academyfinalboy.backend.builders.ActivityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ActivityShould {

  @Test
  void throw_exception_on_team_smaller_than_3() {
    ActivityBuilder activityBuilder = new ActivityBuilder().withMembers(2).withNumberOfGroups(1);

    var actualException =
        Assertions.assertThrows(IllegalStateException.class, activityBuilder::build);

    var exceptionMessage = "can't generate teams with current configuration";
    assertThat(actualException.getMessage()).isEqualTo(exceptionMessage);
  }

  @Test
  void returns_number_of_groups_specified() {
    int numberOfGroups = 2;
    var activity = new ActivityBuilder().withNumberOfGroups(numberOfGroups).build();

    assertThat(activity.getGroups()).hasSize(numberOfGroups);
  }

  @Test
  void splits_evenly_team_members_into_specified_number_of_groups() {
    var activity = new ActivityBuilder().withMembers(4).withNumberOfGroups(2).build();

    activity.getGroups().forEach(group -> assertThat(group.getGrouping()).hasSize(2));
  }

  @Test
  void splits_unevenly_team_members_into_specified_number_of_groups() {
    var activity = new ActivityBuilder().withMembers(5).withNumberOfGroups(3).build();

    activity
        .getGroups()
        .forEach(
            group ->
                assertThat(group.getGrouping()).hasAtLeastOneElementOfType(ActivityMember.class));
  }

  @Test
  void throw_exception_on_number_of_teams_equal_to_members() {
    var activityBuilder = new ActivityBuilder().withMembers(3).withNumberOfGroups(3);
    var actualException =
        Assertions.assertThrows(IllegalStateException.class, activityBuilder::build);

    var exceptionMessage = "can't generate teams with current configuration";
    assertThat(actualException.getMessage()).isEqualTo(exceptionMessage);
  }

  @Test
  void throw_exception_on_number_of_teams_greater_to_members() {
    var activityBuilder = new ActivityBuilder().withMembers(3).withNumberOfGroups(4);

    var actualException =
        Assertions.assertThrows(IllegalStateException.class, activityBuilder::build);

    var exceptionMessage = "can't generate teams with current configuration";
    assertThat(actualException.getMessage()).isEqualTo(exceptionMessage);
  }

  @Test
  void randomize_the_groupings() {
    var activityMembers = generateActivityMembersBy(30);
    var numberOfGroups = 5;

    var groups = partitionBasedOnSize(activityMembers, numberOfGroups).stream().toList();

    Activity activity = new Activity("ECA", activityMembers, numberOfGroups);

    assertThat(activity.getGroups()).isNotEqualTo(groups);
  }
}
