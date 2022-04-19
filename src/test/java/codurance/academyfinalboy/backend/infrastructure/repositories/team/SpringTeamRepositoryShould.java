package codurance.academyfinalboy.backend.infrastructure.repositories.team;

import static org.assertj.core.api.Assertions.assertThat;

import codurance.academyfinalboy.backend.BaseSpringTest;
import codurance.academyfinalboy.backend.model.team.Team;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import codurance.academyfinalboy.backend.model.user.TeamRef;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class SpringTeamRepositoryShould extends BaseSpringTest {

  @Autowired SpringTeamRepository repository;

  @Test
  void save_team() {
    Team team = new Team("team name", "team description", 3L);

    Long teamId = repository.save(team);

    assertThat(teamId).isNotNull();
  }

  @Test
  void find_by_id() {
    Team team = new Team("team name", "team description", 3L);
    Long teamId = repository.save(team);

    Optional<Team> foundTeam = repository.findById(teamId);

    team.setId(teamId);
    assertThat(foundTeam).hasValue(team);
  }

  @Test
  void find_all_by_id() {
    Team team = new Team("team name", "team description", 3L);
    Long teamId = repository.save(team);
    Set<TeamRef> teamRef = Set.of(new TeamRef(teamId));

    var listOfTeams = repository.findAllById(teamRef);

    assertThat(listOfTeams).isEqualTo(List.of(team));

  }
}
