package codurance.academyfinalboy.backend.infrastructure.repositories.team;

import codurance.academyfinalboy.backend.BaseSpringTest;
import codurance.academyfinalboy.backend.model.team.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
