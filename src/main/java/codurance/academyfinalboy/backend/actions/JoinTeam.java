package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.team.TeamService;
import codurance.academyfinalboy.backend.model.token.InvalidTokenException;
import codurance.academyfinalboy.backend.model.token.Token;
import codurance.academyfinalboy.backend.model.token.TokenService;
import codurance.academyfinalboy.backend.model.user.UserService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JoinTeam {

    private final UserService userService;
    private final TokenService tokenService;
    private TeamService teamService;

    public JoinTeam(UserService userService, TokenService tokenService, TeamService teamService) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.teamService = teamService;
    }

    public void execute(UUID joinTokenId) throws Exception {
        userService.getCurrentUser();
        Token token = tokenService.getToken(joinTokenId);
        teamService.getTeam(token.getTeamId());
    }
}
