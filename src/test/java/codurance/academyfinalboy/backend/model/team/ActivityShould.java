package codurance.academyfinalboy.backend.model.team;

import codurance.academyfinalboy.backend.web.controllers.CreateActivityController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class ActivityShould {

  @Test
  void throw_exception_on_team_smaller_than_3() {
    var exceptionMessage = "You can't make groups with less than 3 team members";

    var actualException = Assertions.assertThrows(
        IllegalStateException.class,
        () -> new Activity("name", List.of(new ActivityMember("1"), new ActivityMember("2")), 2));
    assertThat(actualException.getMessage()).isEqualTo(exceptionMessage);
  }

  @Test
  void returns_number_of_groups_specified() {
    var activityMembers = List.of(new ActivityMember("Jon"),
                                                    new ActivityMember("Juan"),
                                                    new ActivityMember("Jose"),
                                                    new ActivityMember("Carl"));
    var numberOfGroups = 2;
    var activity = new Activity("ECA", activityMembers, numberOfGroups);

    assertThat(activity.getGroups()).hasSize(numberOfGroups);
  }

  @Test
  void shuffles_team_members_into_specified_number_of_groups() {
    var activityMembers = new ArrayList<>(List.of(new ActivityMember("Jon"),
            new ActivityMember("Juan"),
            new ActivityMember("Jose"),
            new ActivityMember("Carl")));
    var numberOfGroups = 2;
    var activity = new Activity("ECA", activityMembers, numberOfGroups);

    assertThat(activity.getGroups().get(0)).hasSize(2);
    assertThat(activity.getGroups().get(1)).hasSize(2);

  }

}
