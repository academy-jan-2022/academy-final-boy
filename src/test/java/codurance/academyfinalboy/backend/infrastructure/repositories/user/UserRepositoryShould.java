package codurance.academyfinalboy.backend.infrastructure.repositories.user;

import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryShould {

  @Autowired
  UserRepository repository;

  @Test
  void create_user() {
    User user = new User(UUID.randomUUID(), "fullName");
    User savedUser = repository.save(user);

    assertThat(savedUser.getId()).isNotNull();
  }

  @Test
  void find_user_by_external_id() {

    User user = new User(UUID.randomUUID(), "fullname");
    User savedUser = repository.save(user);

    Optional<User> foundUser = repository.findByExternalId(user.getExternalId());

    assertThat(foundUser).hasValue(savedUser);
  }

  @AfterEach
  void tearDown() {
    repository.clear();

  }

}
