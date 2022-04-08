package codurance.academyfinalboy.backend.actions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import codurance.academyfinalboy.backend.model.user.UserService;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class LoginShould {

  @Test
  void call_create_user() {
    UserService userServiceMock = mock(UserService.class);

    Login login = new Login(userServiceMock);

    String fullName = "fullname";
    UUID externalId = UUID.randomUUID();
    login.execute(externalId, fullName);

    verify(userServiceMock).createUser(externalId, fullName);
  }
}
