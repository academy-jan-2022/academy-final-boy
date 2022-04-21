package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.UserBuilder;
import codurance.academyfinalboy.backend.model.team.TeamService;
import codurance.academyfinalboy.backend.model.token.TokenService;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

class GenerateJoinLinkShould {

  public static final long TEAM_ID = 3L;
  public static final User USER = new UserBuilder().id(12L).build();
  private TeamService mockedTeamService;
  private UserService mockedUserService;
  private GenerateJoinLink generateJoinLink;
  private TokenService mockedTokenService;

  @BeforeEach
  void setUp() {
    mockedTeamService = mock(TeamService.class);
    mockedUserService = mock(UserService.class);
    mockedTokenService = mock(TokenService.class);
    generateJoinLink =
        new GenerateJoinLink(mockedTeamService, mockedUserService, mockedTokenService);
    when(mockedUserService.getCurrentUser()).thenReturn(Optional.of(USER));
  }

  @Test
  void check_that_current_user_is_member_of_team() {
    generateJoinLink.execute(TEAM_ID);

    verify(mockedTeamService).verifyMembership(TEAM_ID, USER.getId());
  }

  @Test
  void call_token_service_if_is_member() {
    when(mockedTeamService.verifyMembership(anyLong(), anyLong())).thenReturn(true);
    generateJoinLink.execute(TEAM_ID);

    verify(mockedTokenService).generateToken(TEAM_ID);
  }

}
