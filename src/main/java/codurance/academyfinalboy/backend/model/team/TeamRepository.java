package codurance.academyfinalboy.backend.model.team;

import codurance.academyfinalboy.backend.model.user.TeamRef;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TeamRepository {
  Optional<Team> findById(Long teamId);

  Long save(Team expectedTeam);

  List<Team> findAllById(Set<TeamRef> teamIds);
  
  void clear();
}
