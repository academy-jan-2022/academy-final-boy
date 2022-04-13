package codurance.academyfinalboy.backend.model.team;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TeamServiceShould {

  @Test
  void create_a_team() {
    TeamRepository mockedTeamRepository = mock(TeamRepository.class);
    TeamService teamService = new TeamService(mockedTeamRepository);

    Team expectedTeam = new Team("team name", "description", 1L);

    teamService.createTeam(1L, "team name", "description");

    verify(mockedTeamRepository).save(expectedTeam);
  }

  @Test
  void get_all_teams_the_user_is_part_of() {
    TeamRepository mockedTeamRepository = mock(TeamRepository.class);
    TeamService teamService = new TeamService(mockedTeamRepository);

    Long userId = 1L;
    Team expectedTeams = new Team("team name", "description", userId);
    List<Team> usersTeams = List.of(expectedTeams);

    when(mockedTeamRepository.findTeamsForUser(userId)).thenReturn(usersTeams);
    teamService.createTeam(1L, "team name", "description");

    List<Team> currentUsersTeam = teamService.getTeamsForUser(userId);

    assertEquals(usersTeams, currentUsersTeam);

  }
}
