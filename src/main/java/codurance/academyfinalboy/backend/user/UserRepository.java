package codurance.academyfinalboy.backend.user;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
  Optional<Participant> findByExternalId(UUID externalId);

  Participant save(Participant participant);
}
