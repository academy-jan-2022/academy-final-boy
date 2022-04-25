package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.team.TeamService;
import codurance.academyfinalboy.backend.model.team.TeamWithMembers;
import org.springframework.stereotype.Component;

@Component
public class GetTeam {

  private final TeamService teamService;

  public GetTeam(TeamService teamService) {
    this.teamService = teamService;
  }

  public TeamWithMembers execute(Long id) throws Exception {
    return teamService.getTeam(id);
  }
}
