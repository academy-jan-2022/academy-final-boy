package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.team.TeamRepository;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetTeamsShould {

  @Mock private UserService mockedUserService;
  @Mock private TeamRepository mockedTeamRepository;

  private User currentUser;

  @BeforeEach
  void setUp() {
    currentUser = new User("1250", "Bob Jones");

    currentUser.addTeam(12L);
    currentUser.addTeam(13L);

    when(mockedUserService.getCurrentUser()).thenReturn(Optional.of(currentUser));

    GetTeams getTeams = new GetTeams(mockedUserService, mockedTeamRepository);
    getTeams.execute();
  }

  @Test
  public void get_current_user_from_user_service() {
    verify(mockedUserService).getCurrentUser();
  }

  @Test
  public void get_teams_from_team_repository() {
    verify(mockedTeamRepository).findAllById(currentUser.getTeams());
  }
}
