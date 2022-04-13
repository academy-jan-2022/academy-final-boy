package codurance.academyfinalboy.backend.infrastructure.repositories.team;

import codurance.academyfinalboy.backend.BaseSpringTest;
import codurance.academyfinalboy.backend.model.team.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class SpringTeamRepositoryShould extends BaseSpringTest {

    @Autowired
    SpringTeamRepository repository;

  @Test
  void save_team() {
      Team  team = new Team("team name", "team description", 3L);

      Long teamId = repository.save(team);

      assertThat(teamId).isNotNull();
  }
}