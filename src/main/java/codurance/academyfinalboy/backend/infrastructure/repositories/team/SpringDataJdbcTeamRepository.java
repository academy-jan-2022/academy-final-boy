package codurance.academyfinalboy.backend.infrastructure.repositories.team;

import codurance.academyfinalboy.backend.model.team.Team;
import org.springframework.data.repository.CrudRepository;

public interface SpringDataJdbcTeamRepository extends CrudRepository<Team, Long> {}
