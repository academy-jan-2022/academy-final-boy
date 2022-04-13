package codurance.academyfinalboy.backend.infrastructure.repositories.user;

import codurance.academyfinalboy.backend.BaseSpringTest;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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

  @AfterEach
  void tearDown() {
    repository.clear();
  }
}
