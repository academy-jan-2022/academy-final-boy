package codurance.academyfinalboy.backend.infrastructure.repositories.team;

import codurance.academyfinalboy.backend.model.team.Team;
import codurance.academyfinalboy.backend.model.team.TeamRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SpringTeamRepository implements TeamRepository {

  private final SpringDataJdbcTeamRepository repository;

  public SpringTeamRepository(SpringDataJdbcTeamRepository repository) {
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
