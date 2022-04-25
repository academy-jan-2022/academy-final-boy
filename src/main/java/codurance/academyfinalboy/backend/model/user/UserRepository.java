package codurance.academyfinalboy.backend.model.user;

import codurance.academyfinalboy.backend.model.team.UserRef;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository {
  Optional<User> findByExternalId(String externalId);

  User save(User user);

  void clear();

  List<User> findAllByIdIn(Set<UserRef> userIds);
}
