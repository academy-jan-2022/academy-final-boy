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

}
