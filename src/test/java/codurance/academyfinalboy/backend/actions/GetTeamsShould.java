package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.team.Team;
import codurance.academyfinalboy.backend.model.team.TeamRepository;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetTeamsShould {

  @Mock private UserService mockedUserService;
  @Mock private TeamRepository mockedTeamRepository;

  private User currentUser;
  private Team team1;
  private Team team2;

  private List<Team> userTeams;

  @BeforeEach
  void setUp() {
    currentUser = new User("1250", "Bob Jones");
    currentUser.setId(2L);

    currentUser.addTeam(12L);
    currentUser.addTeam(13L);

    team1 = new Team("team 1", "team 1 description", currentUser.getId());
    team2 = new Team("team 2", "team 2 description", currentUser.getId());

    when(mockedUserService.getCurrentUser()).thenReturn(Optional.of(currentUser));
    when(mockedTeamRepository.findAllById(currentUser.getTeams()))
        .thenReturn(List.of(team1, team2));

    GetTeams getTeams = new GetTeams(mockedUserService, mockedTeamRepository);
    userTeams = getTeams.execute();
  }

  @Test
  void get_current_user_from_user_service() {
    verify(mockedUserService).getCurrentUser();
  }

  @Test
  void get_teams_from_team_repository() {
    verify(mockedTeamRepository).findAllById(currentUser.getTeams());
  }

  @Test
  void get_a_list_of_teams() {
    assertThat(userTeams).contains(team1, team2);
  }
}
