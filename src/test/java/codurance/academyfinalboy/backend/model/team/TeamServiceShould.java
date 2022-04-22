package codurance.academyfinalboy.backend.model.team;

import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.Mockito.*;

class TeamServiceShould {

  @Test
  void create_a_team() {
    TeamRepository mockedTeamRepository = mock(TeamRepository.class);
    UserService mockedUserService = mock(UserService.class);
    TeamService teamService = new TeamService(mockedTeamRepository, mockedUserService);

    Team expectedTeam = new Team("team name", "description", 1L);

    teamService.createTeam(1L, "team name", "description");

    verify(mockedTeamRepository).save(expectedTeam);
  }

  @Test
  void get_a_team_with_members() {
    Long teamId = 1L;
    TeamRepository mockedTeamRepository = mock(TeamRepository.class);
    UserService mockedUserService = mock(UserService.class);
    TeamService teamService = new TeamService(mockedTeamRepository, mockedUserService);
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
