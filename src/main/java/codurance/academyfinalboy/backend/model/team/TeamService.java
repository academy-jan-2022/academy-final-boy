package codurance.academyfinalboy.backend.model.team;

import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserService;
import java.util.List;
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
      return createTeamWithMembers(teamId);
    }

    throw new Exception("Logged in user doesn't belong to this team");
  }

  public boolean verifyMembership(long teamId, long userId) {
    return teamRepository
        .findById(teamId)
        .map(team -> team.getMembers().contains(new UserRef(userId)))
        .orElse(false);
  }

  private TeamView createTeamWithMembers(Long teamId) {
    Team team = this.teamRepository.findById(teamId).orElseThrow();
    List<User> users = userService.getAllById(team.getMembers());

    return new TeamView(team, users);
  }

  public void addActivity(long teamId, Activity activity) {
    Team team = teamRepository.findById(teamId).orElseThrow();
    team.addActivity(activity);
    teamRepository.save(team);
  }

  public void addUserToTeam(long userId, long teamId) {
    Team team = teamRepository.findById(teamId).orElseThrow();
    team.addMember(userId);
    teamRepository.save(team);
  }
}
