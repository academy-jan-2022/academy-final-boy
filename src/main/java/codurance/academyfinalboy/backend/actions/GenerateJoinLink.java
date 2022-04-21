package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.team.TeamService;
import codurance.academyfinalboy.backend.model.token.TokenService;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GenerateJoinLink {
  private final TeamService teamService;
  private final UserService userService;
  private final TokenService tokenService;

  public GenerateJoinLink(
      TeamService teamService, UserService userService, TokenService tokenService) {

    this.teamService = teamService;
    this.userService = userService;
    this.tokenService = tokenService;
  }

  public UUID execute(long teamId) {
    User currentUser = userService.getCurrentUser().orElseThrow();

    if (currentUserIsMemberOfTheTeam(teamId, currentUser)) {
      return tokenService.generateToken(teamId);
    }

    throw new IllegalStateException("current user isn't a member of the team");
  }

  private boolean currentUserIsMemberOfTheTeam(long teamId, User currentUser) {
    return teamService.verifyMembership(teamId, currentUser.getId());
  }
}
