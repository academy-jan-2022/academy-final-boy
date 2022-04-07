package codurance.academyfinalboy.backend.user;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;



@Repository
public class SpringUserRepository implements UserRepository {

  private final SpringDataJdbcUserRepository repository;

  public SpringUserRepository(SpringDataJdbcUserRepository repository) {

    this.repository = repository;
  }

  @Override
  public Optional<Participant> findByExternalId(UUID externalId) {
    return repository.findByExternalId(externalId);
  }

  @Override
  public Participant save(Participant participant) {
    return repository.save(participant);
  }
}
