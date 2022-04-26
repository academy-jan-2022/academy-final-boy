package codurance.academyfinalboy.backend.model.team;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TeamServiceShould {

  public static final long USER_ID = 1L;
  public static final long TEAM_ID = 2L;
  public static final Team TEAM = new Team("team name", "description", USER_ID);
  private TeamRepository mockedTeamRepository;
  private TeamService teamService;

  @BeforeEach
  void setUp() {
    mockedTeamRepository = mock(TeamRepository.class);
    teamService = new TeamService(mockedTeamRepository);
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
  void add_activity_to_team() {
    var team = new Team("team name", "description", USER_ID);
    when(mockedTeamRepository.findById(TEAM_ID)).thenReturn(Optional.of(team));
    var members = generateActivityMembersBy(4);

    var activity = new Activity("FizzBuzz", members, 2);

    teamService.addActivity(TEAM_ID, activity);
    team.addActivity(activity);

    verify(mockedTeamRepository).save(team);
  }

  List<ActivityMember> generateActivityMembersBy(int numberOfMembers) {
    return new ArrayList<>(
            IntStream.range(0, numberOfMembers)
                    .mapToObj(index -> new ActivityMember(String.valueOf(index)))
                    .toList());
  }
}
