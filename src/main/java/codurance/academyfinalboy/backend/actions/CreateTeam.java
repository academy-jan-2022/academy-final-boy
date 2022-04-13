package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.configurations.AuthenticatedUser;
import codurance.academyfinalboy.backend.model.team.TeamService;
import codurance.academyfinalboy.backend.model.user.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateTeam {
  private final TeamService teamService;
  private final UserService userService;

  public CreateTeam(TeamService teamService, UserService userService) {
    this.teamService = teamService;
    this.userService = userService;
  }

  public Long execute(String name, String description) {
    var user = userService.getCurrentUser().orElseThrow();
    var userId = user.getId();
    var teamId = teamService.createTeam(userId, name, description);
    userService.addTeamToUser(user, teamId);
    return teamId;
  }
}
