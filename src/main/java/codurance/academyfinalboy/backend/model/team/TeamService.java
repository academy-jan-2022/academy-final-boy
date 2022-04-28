package codurance.academyfinalboy.backend.model.team;

import codurance.academyfinalboy.backend.actions.InvalidUserException;
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

  public TeamWithMembers getTeam(Long teamId) throws InvalidUserException, UserNotMemberOfTeamException {
    User currentUser = userService.getCurrentUser().orElseThrow(InvalidUserException::new);
    Long currentUserId = currentUser.getId();

    if (verifyMembership(teamId, currentUserId)) {
      return createTeamWithMembers(teamId);
    }

    throw new UserNotMemberOfTeamException();
  }

  public boolean verifyMembership(long teamId, long userId) {
    return teamRepository
        .findById(teamId)
        .map(team -> team.getMembers().contains(new UserRef(userId)))
        .orElse(false);
  }

  private TeamWithMembers createTeamWithMembers(Long teamId) {
    Team team = this.teamRepository.findById(teamId).orElseThrow();
    List<User> users = userService.getAllById(team.getMembers());

    return new TeamWithMembers(team, users);
  }

  public void addUserToTeam(long userId, long teamId) {
    Team team = teamRepository.findById(teamId).orElseThrow();
    team.addMember(userId);
    teamRepository.save(team);
  }
}
