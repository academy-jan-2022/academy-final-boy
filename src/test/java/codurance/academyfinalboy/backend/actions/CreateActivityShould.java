package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.team.Activity;
import codurance.academyfinalboy.backend.model.team.TeamService;
import codurance.academyfinalboy.backend.web.controllers.CreateActivityController;
import codurance.academyfinalboy.backend.web.controllers.CreateActivityController.ActivityMemberRequest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateActivityShould {

  public static final long TEAM_ID = 1L;

  @Test
  void call_team_service_to_store_activity() {
    var mockedTeamService = mock(TeamService.class);

    var activityMembers =
        IntStream.of(4).mapToObj(i -> new ActivityMemberRequest(i, "Juan" + i)).toList();

    var request =
        new CreateActivityController.CreateActivityRequest(
            TEAM_ID, "activityName", 2, activityMembers);

    CreateActivity createActivity = new CreateActivity(mockedTeamService);

    createActivity.execute(request);

    ArgumentCaptor<Activity> captor = ArgumentCaptor.forClass(Activity.class);

    verify(mockedTeamService).addActivity(eq(TEAM_ID), captor.capture());

    assertThat(captor.getValue().getName()).isEqualTo(request.activityName());
    assertThat(captor.getValue().getGroups()).hasSize(2);
  }
}
