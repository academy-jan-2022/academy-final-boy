package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.team.TeamService;
import codurance.academyfinalboy.backend.model.token.Token;
import codurance.academyfinalboy.backend.model.token.TokenService;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserService;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class JoinTeam {

  private final UserService userService;
  private final TokenService tokenService;
  private final TeamService teamService;

  public JoinTeam(UserService userService, TokenService tokenService, TeamService teamService) {
    this.userService = userService;
    this.tokenService = tokenService;
    this.teamService = teamService;
  }

  public Long execute(UUID joinTokenId) throws Exception {
    Optional<User> user = userService.getCurrentUser();

    if (user.isEmpty()) {
      throw new InvalidUserException();
    }

    Long userId = user.get().getId();
    Token token = tokenService.getToken(joinTokenId);
    teamService.addUserToTeam(userId, token.getTeamId());
    userService.addTeamToUser(user.get(), token.getTeamId());

    return token.getTeamId();
  }
}
