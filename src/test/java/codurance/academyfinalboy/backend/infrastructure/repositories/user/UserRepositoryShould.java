package codurance.academyfinalboy.backend.infrastructure.repositories.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


import codurance.academyfinalboy.backend.BaseSpringTest;
import codurance.academyfinalboy.backend.model.team.UserRef;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserRepositoryShould extends BaseSpringTest {

  @Autowired UserRepository repository;

  @Test
  void create_user() {
    User user = new User("939373333333", "fullName");
    User savedUser = repository.save(user);

    assertThat(savedUser.getId()).isNotNull();
  }

  @Test
  void find_user_by_external_id() {

    User user = new User("123123123123123", "fullname");
    user.addTeam(5L);
    User savedUser = repository.save(user);

    Optional<User> foundUser = repository.findByExternalId(user.getExternalId());

    assertThat(foundUser).hasValue(savedUser);
  }

  @Test
  void clear() {
    User user = new User("123123123123123", "fullname");
    repository.save(user);

    repository.clear();

    assertThat(repository.findByExternalId(user.getExternalId())).isEmpty();
  }
  @Test
  void find_all_users_by_id(){
    User userOne = new User("123123123123123", "fullname");
    User userTwo = new User("123123123123124", "fullname");
    User userOneFromDatabase = repository.save(userOne);
    User userTwoFromDatabase = repository.save(userTwo);
    List<User> expectedUsers = List.of(userOneFromDatabase, userTwoFromDatabase);
    Set<UserRef> userIds= Set.of(new UserRef(userOneFromDatabase.getId()),new UserRef(userTwoFromDatabase.getId()));
    List<User> result = repository.findAllById(userIds);

    assertEquals(expectedUsers, result);
  }


  @AfterEach
  void tearDown() {
    repository.clear();
  }
}
