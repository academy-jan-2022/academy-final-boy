package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.team.TeamService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RemoveUserShould {
    public static final long TEAM_ID = 1L;
    public static final long USER_ID = 1L;

  @Test
  void call_the_team_service_to_remove_the_user_from_a_team() {
      var mockedTeamService = mock(TeamService.class);
      RemoveUser removeUser = new RemoveUser(mockedTeamService);
      removeUser.execute(TEAM_ID);
      verify(mockedTeamService).removeUserFromTeam(USER_ID, TEAM_ID);
  }
}
