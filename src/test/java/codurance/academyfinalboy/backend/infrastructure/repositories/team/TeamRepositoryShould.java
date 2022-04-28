package codurance.academyfinalboy.backend.infrastructure.repositories.team;

import static org.assertj.core.api.Assertions.assertThat;

import codurance.academyfinalboy.backend.BaseSpringTest;
import codurance.academyfinalboy.backend.builders.ActivityBuilder;
import codurance.academyfinalboy.backend.model.team.Activity;
import codurance.academyfinalboy.backend.model.team.Team;
import codurance.academyfinalboy.backend.model.team.TeamRepository;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class TeamRepositoryShould extends BaseSpringTest {

  @Autowired TeamRepository repository;

  @Test
  void save_team() {
    Team team = new Team("team fullName", "team description", 3L);

    Long teamId = repository.save(team);

    assertThat(teamId).isNotNull();
  }

  @Test
  void save_team_with_activity() {
    var activity = new ActivityBuilder().build();

    Team team = new Team("team name", "team description", 3L);
    team.addActivity(activity);

    Long teamId = repository.save(team);

    var foundTeam = repository.findById(teamId).orElseThrow();

    Activity foundActivity = foundTeam.getActivities().get(0);

    assertThat(foundActivity).usingRecursiveComparison().isEqualTo(activity);
  }

  @Test
  void find_by_id() {
    Team team = new Team("team fullName", "team description", 3L);
    Long teamId = repository.save(team);

    Optional<Team> foundTeam = repository.findById(teamId);

    team.setId(teamId);
    assertThat(foundTeam).hasValue(team);
  }

  @Test
  void store_team_with_multiple_activities() {
    Team team = new Team("team fullName", "team description", 3L);
    Activity activity = new ActivityBuilder().build();
    Activity activity2 = new ActivityBuilder().withMembers(5).withName("failing activity").build();
    team.addActivity(activity);
    Long teamId = repository.save(team);

    var foundTeam = repository.findById(teamId).orElseThrow();

    foundTeam.addActivity(activity2);

    repository.save(foundTeam);
    assertThat(repository.findById(teamId).orElseThrow().getActivities()).hasSize(2);
  }

  @Test
  void clear() {
    Team team = new Team("team fullName", "team description", 3L);
    Long teamId = repository.save(team);

    repository.clear();

    assertThat(repository.findById(teamId)).isEmpty();
  }

  @AfterEach
  void tearDown() {
    repository.clear();
  }
}
