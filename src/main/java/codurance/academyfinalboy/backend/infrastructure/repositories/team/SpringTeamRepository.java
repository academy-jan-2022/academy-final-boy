package codurance.academyfinalboy.backend.infrastructure.repositories.team;

import codurance.academyfinalboy.backend.model.team.Team;
import codurance.academyfinalboy.backend.model.team.TeamRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import codurance.academyfinalboy.backend.model.user.TeamRef;
import org.springframework.stereotype.Repository;

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
  public List<Team> findAllById(Set<TeamRef> teamIds) {
    throw new UnsupportedOperationException();
  }
}
