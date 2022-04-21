package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.UserBuilder;
import codurance.academyfinalboy.backend.model.team.TeamService;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

class GenerateJoinLinkShould {

  public static final long TEAM_ID = 3L;
  public static final User USER = new UserBuilder().id(12L).build();

  @Test
  void check_that_current_user_is_member_of_team() {
    TeamService mockedTeamService = mock(TeamService.class);
    UserService mockedUserService = mock(UserService.class);
    GenerateJoinLink generateJoinLink = new GenerateJoinLink(mockedTeamService, mockedUserService);

    when(mockedUserService.getCurrentUser()).thenReturn(Optional.of(USER));

    generateJoinLink.execute(TEAM_ID);

    verify(mockedTeamService).verifyMembership(TEAM_ID, USER.getId());
  }
}
