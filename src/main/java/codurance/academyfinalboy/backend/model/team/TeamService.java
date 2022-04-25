package codurance.academyfinalboy.backend.model.team;

import org.springframework.stereotype.Service;

@Service
public class TeamService {
  private final TeamRepository teamRepository;

  public TeamService(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  public Long createTeam(Long userId, String name, String description) {
    Team team = new Team(name, description, userId);
    return teamRepository.save(team);
  }

  public boolean verifyMembership(long teamId, long userId) {
    return teamRepository
        .findById(teamId)
        .map(team -> team.getMembers().contains(new UserRef(userId)))
        .orElse(false);
  }

  public void addActivity(long teamId, Activity activity) {
    throw new UnsupportedOperationException();
  }
}
