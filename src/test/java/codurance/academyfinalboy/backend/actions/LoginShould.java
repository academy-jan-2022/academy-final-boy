package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.user.UserService;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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