package codurance.academyfinalboy.backend.user;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class UserServiceShould {

  @Test
  void create_user_if_does_not_exists() {
    UserRepository userRepositoryMock = mock(UserRepository.class);

    UserService userService = new UserService(userRepositoryMock);

    UUID externalId = UUID.randomUUID();
    String fullName = "Mario Sanchez Lopez";
    userService.createUser(externalId, fullName);

    var expectedUser = new User(externalId, fullName, "MarioSL");

    verify(userRepositoryMock).save(expectedUser);
  }
}
