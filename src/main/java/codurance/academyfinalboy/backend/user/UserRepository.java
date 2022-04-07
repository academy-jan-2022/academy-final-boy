package codurance.academyfinalboy.backend.user;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

  Optional<User> findByExternalId(UUID externalId);

  User save(User user);
}
