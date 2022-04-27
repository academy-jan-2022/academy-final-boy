package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.token.TokenService;
import codurance.academyfinalboy.backend.model.user.UserService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JoinTeam {

    private final UserService userService;
    private TokenService tokenService;

    public JoinTeam(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    public void execute(UUID joinTokenId) {
        userService.getCurrentUser();
        tokenService.getToken(joinTokenId);
    }
}
