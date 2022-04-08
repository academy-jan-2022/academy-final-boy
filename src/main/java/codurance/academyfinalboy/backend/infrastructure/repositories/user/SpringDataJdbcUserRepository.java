package codurance.academyfinalboy.backend.infrastructure.repositories.user;

import codurance.academyfinalboy.backend.model.user.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface SpringDataJdbcUserRepository extends CrudRepository<User, Long> {

  Optional<User> findByExternalId(String externalId);
}
