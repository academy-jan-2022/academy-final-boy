package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.team.TeamService;
import codurance.academyfinalboy.backend.model.team.TeamView;
import org.springframework.stereotype.Component;

@Component
public class GetTeam {
    public GetTeam(TeamService teamService) {

    }

    public TeamView execute() {
        throw new UnsupportedOperationException();
    }
}
