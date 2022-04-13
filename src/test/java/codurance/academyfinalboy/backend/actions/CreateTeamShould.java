package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.configurations.AuthenticatedUser;
import codurance.academyfinalboy.backend.model.team.TeamService;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

class CreateTeamShould {
  TeamService mockedTeamService;
  UserService mockedUserService;
  CreateTeam createTeam;
  private final String externalId = "externalId";
  User savedUser = new User(externalId, "name");
  private final Long userId = 2L;
  private final Long teamId = 3L;

  @BeforeEach
  void setUp() {
    savedUser.setId(userId);

    mockedTeamService = mock(TeamService.class);
    mockedUserService = mock(UserService.class);

    when(mockedUserService.getCurrentUser()).thenReturn(Optional.of(savedUser));
    when(mockedTeamService.createTeam(userId, "team name", "description")).thenReturn(teamId);

    createTeam = new CreateTeam(mockedTeamService, mockedUserService);
    createTeam.execute("team name", "description");
  }

  @Test
  void get_current_user_from_user_service() {
    verify(mockedUserService).getCurrentUser();
  }

  @Test
  void call_create_team_service() {
    verify(mockedTeamService).createTeam(userId, "team name", "description");
  }

  @Test
  void add_team_to_user() {
    verify(mockedUserService).addTeamToUser(savedUser, teamId);
  }
}
