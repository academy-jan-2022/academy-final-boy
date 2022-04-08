package codurance.academyfinalboy.backend.model.user;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
  Optional<User> findByExternalId(String externalId);

  User save(User user);

  void clear();
}
