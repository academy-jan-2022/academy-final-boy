package codurance.academyfinalboy.backend.infrastructure.repositories.user;

import codurance.academyfinalboy.backend.model.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataJdbcUserRepository extends CrudRepository<User, Long> {

  Optional<User> findByExternalId(UUID externalId);
}
