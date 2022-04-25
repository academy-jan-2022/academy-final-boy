package codurance.academyfinalboy.backend.infrastructure.repositories.token;

import codurance.academyfinalboy.backend.model.token.Token;
import org.springframework.data.repository.CrudRepository;

public interface SpringDataJdbcTokenRepository extends CrudRepository<Token, Long> {}
