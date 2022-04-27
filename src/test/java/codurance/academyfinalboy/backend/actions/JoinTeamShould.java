package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.team.TeamService;
import codurance.academyfinalboy.backend.model.token.InvalidTokenException;
import codurance.academyfinalboy.backend.model.token.TimeProvider;
import codurance.academyfinalboy.backend.model.token.Token;
import codurance.academyfinalboy.backend.model.token.TokenService;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import org.mockito.Mock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JoinTeamShould {

  public static final long TEAM_ID = 3L;
  @Mock private UserService mockedUserService;

  @Mock private TokenService mockedTokenService;

  @Mock private TeamService mockedTeamService;

  private UUID joinTokenId;
  private Token token;
  private User user;
  private Long actionResult;

  @Nested
  class UserExists {
    @BeforeEach
    void setUp() throws Exception {
      JoinTeam joinTeamAction = new JoinTeam(mockedUserService, mockedTokenService, mockedTeamService);

      joinTokenId = UUID.randomUUID();
      token = new Token(TEAM_ID, joinTokenId, new TimeProvider().getCurrentTime().plusMinutes(5));
      user = new User("3234LK", "Full name");
      user.setId(2L);

      when(mockedUserService.getCurrentUser()).thenReturn(Optional.of(user));
      when(mockedTokenService.getToken(joinTokenId)).thenReturn(token);
      actionResult = joinTeamAction.execute(joinTokenId);
    }

    @Test
    void get_the_current_user() {
      verify(mockedUserService).getCurrentUser();
    }

    @Test
    void call_the_token_service_to_validate_the_token() throws InvalidTokenException {
      verify(mockedTokenService).getToken(joinTokenId);
    }

    @Test void
    add_user_to_the_team() {
      verify(mockedTeamService).addUserToTeam(user.getId(),TEAM_ID);
    }

    @Test void
    add_team_to_the_user() {
      verify(mockedUserService).addTeamToUser(user, TEAM_ID);
    }

    @Test void
    return_the_team_id() {
      assertThat(actionResult, equalTo(TEAM_ID));
    }
  }

  @Nested
  class UserDoesNotExist {
    @Test void
    throw_invalid_user_exception_when_user_does_not_exist() throws Exception {
      JoinTeam joinTeamAction = new JoinTeam(mockedUserService, mockedTokenService, mockedTeamService);

      when(mockedUserService.getCurrentUser()).thenReturn(Optional.empty());

      Exception exception =
              assertThrows(InvalidUserException.class, () -> joinTeamAction.execute(joinTokenId));
      String expectedMessage = "User to add does not exist";
      String actualMessage = exception.getMessage();

      assertEquals(expectedMessage, actualMessage);
    }
  }
}
