package codurance.academyfinalboy.backend.model.team;

import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
  private final TeamRepository teamRepository;
  private final UserService userService;

  public TeamService(TeamRepository teamRepository, UserService userService) {
    this.teamRepository = teamRepository;
    this.userService = userService;
  }

  public Long createTeam(Long userId, String name, String description) {
    Team team = new Team(name, description, userId);

    return teamRepository.save(team);
  }

  public TeamView getTeam(Long teamId) throws Exception {
    User currentUser = userService.getCurrentUser().orElseThrow();
    Long currentUserId = currentUser.getId();

    if (verifyMembership(teamId, currentUserId)) {
      Optional<Team> team = this.teamRepository.findById(teamId);

      if (team.isPresent()) {
        List<User> users = userService.getAllById(team.get().getMembers());
        return new TeamView(team.get(), users);
      }
      return null;
    }
    throw new Exception("Logged in user doesn't belong to this team");
  }

  public boolean verifyMembership(long teamId, long userId) {
    return teamRepository
        .findById(teamId)
        .map(team -> team.getMembers().contains(new UserRef(userId)))
        .orElse(false);
  }
}
