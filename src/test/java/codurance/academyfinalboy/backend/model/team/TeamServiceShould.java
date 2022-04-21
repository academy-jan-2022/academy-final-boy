package codurance.academyfinalboy.backend.model.team;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TeamServiceShould {

  public static final long USER_ID = 1L;
  public static final long TEAM_ID = 2L;
  public static final Team EXPECTED_TEAM = new Team("team name", "description", USER_ID);
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

    verify(mockedTeamRepository).save(EXPECTED_TEAM);
  }

  @Test
  void return_true_if_user_is_member_of_team() {
    when(mockedTeamRepository.findById(TEAM_ID)).thenReturn(Optional.of(EXPECTED_TEAM));

    assertThat(teamService.verifyMembership(TEAM_ID, USER_ID)).isTrue();
  }
}
