package codurance.academyfinalboy.backend.model.user;

import java.util.Optional;

public interface UserRepository {
  Optional<User> findByExternalId(String externalId);

  User save(User user);

  void clear();
}
