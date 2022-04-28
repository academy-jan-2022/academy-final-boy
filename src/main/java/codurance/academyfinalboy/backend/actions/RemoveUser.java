package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.team.TeamService;
import codurance.academyfinalboy.backend.model.user.UserService;
import org.springframework.stereotype.Component;

@Component
public class RemoveUser {
    private UserService userService;
    private TeamService teamService;

    public RemoveUser(UserService userService, TeamService teamService) {
        this.userService = userService;
        this.teamService = teamService;
    }

    public void execute(Long teamId) {
        var userId = userService.getCurrentUser().orElseThrow().getId();
        teamService.removeUserFromTeam(userId, teamId);
    }
}
