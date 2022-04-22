package codurance.academyfinalboy.backend.actions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import codurance.academyfinalboy.backend.UserBuilder;
import codurance.academyfinalboy.backend.model.team.TeamService;
import codurance.academyfinalboy.backend.model.token.TokenService;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserService;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GenerateJoinLinkShould {

  public static final long TEAM_ID = 3L;
  public static final User USER = new UserBuilder().id(12L).build();
  public static final UUID TOKEN = UUID.randomUUID();
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
    when(mockedTeamService.verifyMembership(anyLong(), anyLong())).thenReturn(true);

    generateJoinLink.execute(TEAM_ID);

    verify(mockedTeamService).verifyMembership(TEAM_ID, USER.getId());
  }

  @Test
  void call_token_service_if_is_member() {
    when(mockedTeamService.verifyMembership(anyLong(), anyLong())).thenReturn(true);
    generateJoinLink.execute(TEAM_ID);

    verify(mockedTokenService).generateToken(TEAM_ID);
  }

  @Test
  void returns_generated_token_if_is_member() {
    when(mockedTeamService.verifyMembership(anyLong(), anyLong())).thenReturn(true);
    when(mockedTokenService.generateToken(TEAM_ID)).thenReturn(TOKEN);

    UUID generatedToken = generateJoinLink.execute(TEAM_ID);

    assertThat(generatedToken).isEqualTo(TOKEN);
  }

  @Test
  void throws_exception_if_is_not_member() {
    when(mockedTeamService.verifyMembership(anyLong(), anyLong())).thenReturn(false);
    Assertions.assertThrows(IllegalStateException.class, () -> generateJoinLink.execute(TEAM_ID));

    verify(mockedTokenService, never()).generateToken(TEAM_ID);
  }
}
