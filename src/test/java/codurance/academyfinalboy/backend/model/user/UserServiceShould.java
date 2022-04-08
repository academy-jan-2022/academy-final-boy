package codurance.academyfinalboy.backend.model.user;

import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserServiceShould {

  private UserRepository userRepositoryMock;
  private UserService userService;

  @BeforeEach
  void setUp() {
    userRepositoryMock = mock(UserRepository.class);
    userService = new UserService(userRepositoryMock);
  }

  @Test
  void create_user_if_does_not_exists() {
    UUID externalId = UUID.randomUUID();
    String fullName = "Mario Sanchez Lopez";
    userService.createUser(externalId, fullName);

    var expectedUser = new User(externalId, fullName);

    verify(userRepositoryMock).save(expectedUser);
  }

  @Test
  void not_create_user_if_already_exists() {

    UUID externalId = UUID.randomUUID();
    String fullName = "Mario Sanchez Lopez";
    var expectedUser = new User(externalId, fullName);
    when(userRepositoryMock.findByExternalId(externalId)).thenReturn(Optional.of(expectedUser));

    userService.createUser(externalId, fullName);

    verify(userRepositoryMock, never()).save(expectedUser);
  }
}
