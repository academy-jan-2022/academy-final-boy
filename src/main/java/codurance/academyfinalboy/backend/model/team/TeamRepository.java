package codurance.academyfinalboy.backend.model.team;

import java.util.List;
import java.util.Optional;

public interface TeamRepository {
  Optional<Team> findById(Long teamId);

  Long save(Team expectedTeam);

    List<Team> findTeamsByUser(Long userId);
}
