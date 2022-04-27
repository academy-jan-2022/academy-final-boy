package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.team.TeamService;
import codurance.academyfinalboy.backend.model.token.*;
import codurance.academyfinalboy.backend.model.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JoinTeamShould {

  @Mock private UserService mockedUserService;

  @Mock private TokenService mockedTokenService;

  @Mock private TeamService mockedTeamService;

  private UUID joinTokenId;
  private Token token;

  @BeforeEach
  void setUp() throws Exception {
    JoinTeam joinTeamAction = new JoinTeam(mockedUserService, mockedTokenService, mockedTeamService);
    joinTokenId = UUID.randomUUID();
    token = new Token(3L, joinTokenId, new TimeProvider().getCurrentTime().plusMinutes(5));
    when(mockedTokenService.getToken(joinTokenId)).thenReturn(token);
    joinTeamAction.execute(joinTokenId);
  }

  @Test
  void get_the_current_user() {
    verify(mockedUserService).getCurrentUser();
  }

  @Test
  void call_the_token_service_to_validate_the_token() throws InvalidTokenException {

    verify(mockedTokenService).getToken(joinTokenId);
  }

  @Test
  void find_team_to_join() throws Exception {
    verify(mockedTeamService).getTeam(token.getTeamId());
  }
}
