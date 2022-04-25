package codurance.academyfinalboy.backend.model.team;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import codurance.academyfinalboy.backend.UserBuilder;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TeamServiceShould {

  public static final long USER_ID = 1L;
  public static final long TEAM_ID = 2L;
  public static final Team TEAM = new Team("team fullName", "description", USER_ID);
  private TeamRepository mockedTeamRepository;
  private UserService mockedUserService;
  private TeamService teamService;

  @BeforeEach
  void setUp() {
    mockedTeamRepository = mock(TeamRepository.class);
    mockedUserService = mock(UserService.class);
    teamService = new TeamService(mockedTeamRepository, mockedUserService);
  }

  @Test
  void create_a_team() {
    teamService.createTeam(USER_ID, "team fullName", "description");

    verify(mockedTeamRepository).save(TEAM);
  }

  @Test
  void return_true_if_user_is_member_of_team() {
    when(mockedTeamRepository.findById(TEAM_ID)).thenReturn(Optional.of(TEAM));

    assertThat(teamService.verifyMembership(TEAM_ID, USER_ID)).isTrue();
  }

  @Test
  void return_false_if_team_does_not_exist() {
    assertThat(teamService.verifyMembership(TEAM_ID, USER_ID)).isFalse();
  }

  @Test
  void return_false_if_user_does_not_exist() {
    when(mockedTeamRepository.findById(TEAM_ID)).thenReturn(Optional.of(TEAM));

    assertThat(teamService.verifyMembership(TEAM_ID, 123L)).isFalse();
  }

  @Test
  void get_a_team_with_members() throws Exception {
    Long teamId = 1L;

    Team teamFromRepository = new Team("team fullName", "description", 1L);

    teamFromRepository.setId(teamId);

    User user = new UserBuilder().id(1L).build();

    when(mockedTeamRepository.findById(teamId)).thenReturn(Optional.of(teamFromRepository));

    when(mockedUserService.getAllById(teamFromRepository.getMembers())).thenReturn((List.of(user)));
    when(mockedUserService.getCurrentUser()).thenReturn(Optional.of(user));

    TeamView expectedTeam = new TeamView(teamFromRepository, List.of(user));

    TeamView team = teamService.getTeam(teamId);

    verify(mockedTeamRepository, times(2)).findById(teamId);
    verify(mockedUserService).getAllById(teamFromRepository.getMembers());

    assertThat(expectedTeam, samePropertyValuesAs(team));
  }

  @Test
  void not_get_team_if_the_user_requesting_is_not_a_member_of_the_team() {
    Long teamId = 1L;

    Team teamFromRepository = new Team("team fullName", "description", 1L);

    teamFromRepository.setId(teamId);

    User user = new UserBuilder().id(2L).build();

    when(mockedTeamRepository.findById(teamId)).thenReturn(Optional.of(teamFromRepository));
    when(mockedUserService.getCurrentUser()).thenReturn(Optional.of(user));

    Exception exception =
        assertThrows(
            Exception.class,
            () -> teamService.getTeam(teamId));

    String expectedMessage = "Logged in user doesn't belong to this team";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
}
