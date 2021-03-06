package codurance.academyfinalboy.backend.model.team;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import codurance.academyfinalboy.backend.actions.InvalidUserException;
import codurance.academyfinalboy.backend.builders.ActivityBuilder;
import codurance.academyfinalboy.backend.builders.UserBuilder;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TeamServiceShould {

  public static final long USER_ID = 1L;
  public static final long TEAM_ID = 2L;
  public static final String TEAM_NAME = "team fullName";
  public static final String TEAM_DESCRIPTION = "description";
  public Team savedTeam;
  private TeamRepository mockedTeamRepository;
  private UserService mockedUserService;
  private TeamService teamService;

  @BeforeEach
  void setUp() {
    mockedTeamRepository = mock(TeamRepository.class);
    mockedUserService = mock(UserService.class);
    teamService = new TeamService(mockedTeamRepository, mockedUserService);
    savedTeam = new Team(TEAM_NAME, TEAM_DESCRIPTION, USER_ID);
  }

  @Test
  void create_a_team() {
    teamService.createTeam(USER_ID, TEAM_NAME, TEAM_DESCRIPTION);

    verify(mockedTeamRepository).save(savedTeam);
  }

  @Test
  void return_true_if_user_is_member_of_team() {
    when(mockedTeamRepository.findById(TEAM_ID)).thenReturn(Optional.of(savedTeam));

    assertThat(teamService.verifyMembership(TEAM_ID, USER_ID)).isTrue();
  }

  @Test
  void return_false_if_team_does_not_exist() {
    assertThat(teamService.verifyMembership(TEAM_ID, USER_ID)).isFalse();
  }

  @Test
  void return_false_if_user_does_not_exist() {
    when(mockedTeamRepository.findById(TEAM_ID)).thenReturn(Optional.of(savedTeam));

    assertThat(teamService.verifyMembership(TEAM_ID, 123L)).isFalse();
  }

  @Test
  void get_a_teamview() throws InvalidUserException, UserNotMemberOfTeamException {
    Activity activity = new ActivityBuilder().build();
    savedTeam.setId(TEAM_ID);
    savedTeam.addActivity(activity);

    User user = new UserBuilder().id(USER_ID).build();

    when(mockedTeamRepository.findById(TEAM_ID)).thenReturn(Optional.of(savedTeam));

    when(mockedUserService.getAllById(savedTeam.getMembers())).thenReturn((List.of(user)));
    when(mockedUserService.getCurrentUser()).thenReturn(Optional.of(user));

    TeamView expectedTeam = new TeamView(savedTeam, List.of(user));

    TeamView team = teamService.getTeam(TEAM_ID);

    verify(mockedTeamRepository, times(2)).findById(TEAM_ID);
    verify(mockedUserService).getAllById(savedTeam.getMembers());

    assertThat(expectedTeam, samePropertyValuesAs(team));
  }

  @Test
  void not_get_team_if_the_user_requesting_is_not_a_member_of_the_team() {
    savedTeam.setId(TEAM_ID);
    User user = new UserBuilder().id(2L).build();

    when(mockedTeamRepository.findById(TEAM_ID)).thenReturn(Optional.of(savedTeam));
    when(mockedUserService.getCurrentUser()).thenReturn(Optional.of(user));

    UserNotMemberOfTeamException exception =
        assertThrows(UserNotMemberOfTeamException.class, () -> teamService.getTeam(TEAM_ID));

    String expectedMessage = "Logged in user doesn't belong to this team";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void add_activity_to_team() {
    var team = new Team("team name", "description", USER_ID);
    when(mockedTeamRepository.findById(TEAM_ID)).thenReturn(Optional.of(team));

    Activity activity = new ActivityBuilder().build();

    teamService.addActivity(TEAM_ID, activity);
    team.addActivity(activity);

    verify(mockedTeamRepository).save(team);
  }

  @Test
  void add_a_user_to_a_team_if_the_team_exists() {
    long newMemberId = 50L;

    Team mockedTeam = mock(Team.class);
    when(mockedTeamRepository.findById(TEAM_ID)).thenReturn(Optional.of(mockedTeam));

    teamService.addUserToTeam(newMemberId, TEAM_ID);

    verify(mockedTeamRepository).findById(TEAM_ID);
    verify(mockedTeam).addMember(newMemberId);
    verify(mockedTeamRepository).save(mockedTeam);
  }

  @Test
  void remove_a_user_from_a_team() {
    long currentMemberId = 5L;

    Team mockedTeam = mock(Team.class);
    when(mockedTeamRepository.findById(TEAM_ID)).thenReturn(Optional.of(mockedTeam));

    teamService.removeUserFromTeam(currentMemberId, TEAM_ID);
    verify(mockedTeamRepository).findById(TEAM_ID);
    verify(mockedTeam).removeMember(currentMemberId);
    verify(mockedTeamRepository).save(mockedTeam);
  }
}
