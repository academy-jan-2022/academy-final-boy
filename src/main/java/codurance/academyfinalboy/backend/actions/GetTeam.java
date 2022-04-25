package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.team.TeamService;
import codurance.academyfinalboy.backend.model.team.TeamView;
import org.springframework.stereotype.Component;

@Component
public class GetTeam {

    private TeamService teamService;

    public GetTeam(TeamService teamService) {
        this.teamService = teamService;
    }

    public TeamView execute(Long id) throws Exception {
        return teamService.getTeam(id);
    }
}
