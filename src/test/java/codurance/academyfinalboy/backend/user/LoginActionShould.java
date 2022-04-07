package codurance.academyfinalboy.backend.user;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class LoginActionShould {

  @Test
  void call_create_user() {
    UserService userServiceMock = mock(UserService.class);

    LoginAction loginAction = new LoginAction(userServiceMock);

    String fullName = "fullname";
    UUID externalId = UUID.randomUUID();
    loginAction.execute(externalId, fullName);

    verify(userServiceMock).createUser(externalId, fullName);
  }
}
