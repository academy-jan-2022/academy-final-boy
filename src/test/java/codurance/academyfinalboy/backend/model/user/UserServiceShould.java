package codurance.academyfinalboy.backend.model.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import codurance.academyfinalboy.backend.configurations.AuthenticatedUser;
import java.util.Optional;
import java.util.Set;

import codurance.academyfinalboy.backend.model.team.UserRef;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserServiceShould {

  private UserRepository userRepositoryMock;
  private UserService userService;
  private AuthenticatedUser mockAuthenticatedUser;

  @BeforeEach
  void setUp() {
    mockAuthenticatedUser = mock(AuthenticatedUser.class);
    userRepositoryMock = mock(UserRepository.class);
    userService = new UserService(userRepositoryMock, mockAuthenticatedUser);
  }

  @Test
  void create_user_if_does_not_exists() {
    String externalId = "12322333333";
    String fullName = "Mario Sanchez Lopez";
    userService.createUser(externalId, fullName);

    var expectedUser = new User(externalId, fullName);

    verify(userRepositoryMock).save(expectedUser);
  }

  @Test
  void not_create_user_if_already_exists() {

    String externalId = "asdfsadfsadf";
    String fullName = "Mario Sanchez Lopez";
    var expectedUser = new User(externalId, fullName);
    when(userRepositoryMock.findByExternalId(externalId)).thenReturn(Optional.of(expectedUser));

    userService.createUser(externalId, fullName);

    verify(userRepositoryMock, never()).save(expectedUser);
  }

  @Test
  void get_current_user_with_external_id() {
    String externalId = "asdfsadfsadf";
    when(mockAuthenticatedUser.getExternalId()).thenReturn(externalId);
    userService.getCurrentUser();
    verify(userRepositoryMock).findByExternalId(externalId);
  }

  @Test
  void add_team_to_a_user() {
    var user = new User("external id", "full name");
    var teamId = 3L;

    userService.addTeamToUser(user, teamId);
    assertThat(user.getTeams()).contains(new TeamRef(teamId));
    verify(userRepositoryMock).save(user);
  }

  @Test
  void find_all_users_by_id(){
    var userOne = new User("externalIdOne", "full name");
    var userTwo = new User("externalIdTwo", "name full");

    userOne.setId(1L);
    userTwo.setId(2L);

    var usersRefs = Set.of(new UserRef(userOne.id), new UserRef(userTwo.id));

    userService.getAllById(usersRefs);

    verify(userRepositoryMock).findAllByIdIn(usersRefs);
  }
}
