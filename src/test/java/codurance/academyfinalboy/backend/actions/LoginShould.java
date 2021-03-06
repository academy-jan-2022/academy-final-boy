package codurance.academyfinalboy.backend.actions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import codurance.academyfinalboy.backend.model.user.UserService;
import org.junit.jupiter.api.Test;

class LoginShould {

  @Test
  void call_create_user() {
    UserService userServiceMock = mock(UserService.class);

    Login login = new Login(userServiceMock);

    String fullName = "fullname";
    String externalId = "51656156156";
    login.execute(externalId, fullName);

    verify(userServiceMock).createUser(externalId, fullName);
  }
}
