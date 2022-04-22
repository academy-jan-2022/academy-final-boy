package codurance.academyfinalboy.backend.infrastructure.repositories.user;

import codurance.academyfinalboy.backend.model.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataJdbcUserRepository extends CrudRepository<User, Long> {

  Optional<User> findByExternalId(String externalId);

  List<User> findAllByIdIn(List<Long> userIds);
}
