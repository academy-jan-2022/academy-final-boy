package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.token.TokenService;
import codurance.academyfinalboy.backend.model.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class JoinTeamShould {

    @Mock
    private UserService mockedUserService;

    @Mock
    private TokenService mockedTokenService;

    private UUID joinTokenId;

  @BeforeEach
  void setUp() {
      JoinTeam joinTeamAction = new JoinTeam(mockedUserService, mockedTokenService);
      joinTokenId = UUID.randomUUID();

      joinTeamAction.execute(joinTokenId);
  }

    @Test void
    get_the_current_user() {
        verify(mockedUserService).getCurrentUser();
    }

    @Test void
    call_the_token_service_to_validate_the_token() {
        verify(mockedTokenService).getToken(joinTokenId);
    }


}
