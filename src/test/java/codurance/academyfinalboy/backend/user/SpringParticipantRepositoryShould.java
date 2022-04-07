package codurance.academyfinalboy.backend.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class SpringParticipantRepositoryShould {

  @Autowired UserRepository repository;

  @Test
  void createUser() {
    Participant participant = new Participant(UUID.randomUUID(), "fullName", "username");
    Participant savedParticipant = repository.save(participant);

    assertThat(savedParticipant.getId()).isNotNull();
  }
}
