package codurance.academyfinalboy.backend.user;

import java.util.UUID;

public interface UserRepository {

  User findByExternalId(UUID externalId);

  User save(User user);
}
