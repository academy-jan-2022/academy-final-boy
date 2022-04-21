package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.team.TeamService;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GenerateJoinLink {
  private final TeamService teamService;
  private final UserService userService;

  public GenerateJoinLink(TeamService teamService, UserService userService, Object mockedTokenService) {

    this.teamService = teamService;
    this.userService = userService;
  }

  public UUID execute(long teamId) {
    User currentUser = userService.getCurrentUser().orElseThrow();

    teamService.verifyMembership(teamId, currentUser.getId());


    return null;
  }
}
