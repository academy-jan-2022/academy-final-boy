package codurance.academyfinalboy.backend.model.team;

import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserService;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.Mockito.*;

class TeamServiceShould {

  public static final long USER_ID = 1L;
  public static final long TEAM_ID = 2L;
  public static final Team TEAM = new Team("team name", "description", USER_ID);
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
    teamService.createTeam(USER_ID, "team name", "description");

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
  void get_a_team_with_members() {
    Long teamId = 1L;
    
    Team teamFromRepository = new Team("team name", "description", 1L);

    teamFromRepository.setId(teamId);

    User user = new User("anExternalId", "Full Name");
    user.setId(2L);

    when(mockedTeamRepository.findById(teamId)).thenReturn(Optional.of(teamFromRepository));

    when(mockedUserService.getAllById(teamFromRepository.getMembers())).thenReturn((List.of(user)));

    TeamView expectedTeam = new TeamView(teamFromRepository, List.of(user));

    TeamView team = teamService.getTeam(teamId);

    verify(mockedTeamRepository).findById(teamId);
    verify(mockedUserService).getAllById(teamFromRepository.getMembers());

    assertThat(expectedTeam, samePropertyValuesAs(team));
  }
}
