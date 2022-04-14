package codurance.academyfinalboy.backend.infrastructure.repositories.team;

import codurance.academyfinalboy.backend.model.team.Team;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class TeamRepository implements codurance.academyfinalboy.backend.model.team.TeamRepository {

  private final SpringDataJdbcTeamRepository repository;

  public TeamRepository(SpringDataJdbcTeamRepository repository) {
    this.repository = repository;
  }

  @Override
  public Optional<Team> findById(Long teamId) {
    return repository.findById(teamId);
  }

  @Override
  public Long save(Team team) {
    Team savedTeam = repository.save(team);
    return savedTeam.getId();
  }

  @Override
  public void clear() {
    repository.deleteAll();
  }
}
