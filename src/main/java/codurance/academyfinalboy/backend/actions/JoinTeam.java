package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.user.UserService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JoinTeam {

    private final UserService userService;

    public JoinTeam(UserService userService) {
        this.userService = userService;
    }

    public void execute(UUID joinTokenId) {
        userService.getCurrentUser();
    }
}
