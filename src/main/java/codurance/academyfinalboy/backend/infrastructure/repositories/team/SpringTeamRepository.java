package codurance.academyfinalboy.backend.infrastructure.repositories.team;

import codurance.academyfinalboy.backend.model.team.Team;
import codurance.academyfinalboy.backend.model.team.TeamRepository;
import codurance.academyfinalboy.backend.model.user.TeamRef;
import java.util.*;
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

    List<Long> teamIdList = teamIds.stream().map(teamRef -> teamRef.getTeamId().getId()).toList();

    var result = new ArrayList<Team>();
    repository.findAllById(teamIdList).forEach(result::add);

    return result;
  }
}
