package codurance.academyfinalboy.backend.actions;

import static org.mockito.Mockito.*;

import codurance.academyfinalboy.backend.builders.UserBuilder;
import codurance.academyfinalboy.backend.model.team.TeamService;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserService;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class RemoveUserShould {
  public static final long TEAM_ID = 1L;
  public static final long USER_ID = 1L;

  @Test
  void call_the_team_service_to_remove_the_user_from_a_team() {
    var mockedTeamService = mock(TeamService.class);
    var mockedUserService = mock(UserService.class);
    RemoveUser removeUser = new RemoveUser(mockedUserService, mockedTeamService);

    User user = new UserBuilder().id(USER_ID).build();

    when(mockedUserService.getCurrentUser()).thenReturn(Optional.of(user));

    removeUser.execute(TEAM_ID);
    verify(mockedTeamService).removeUserFromTeam(USER_ID, TEAM_ID);
  }
}
