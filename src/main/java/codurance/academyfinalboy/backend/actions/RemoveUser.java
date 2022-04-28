package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.team.TeamService;
import org.springframework.stereotype.Component;

@Component
public class RemoveUser {
    private TeamService teamService;

    public RemoveUser(TeamService teamService) {
        this.teamService = teamService;
    }

    public void execute(Long teamId) {
        throw new UnsupportedOperationException();
    }
}
