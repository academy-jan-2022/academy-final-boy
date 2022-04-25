package codurance.academyfinalboy.backend.web.controllers;

import codurance.academyfinalboy.backend.actions.GetTeam;
import codurance.academyfinalboy.backend.model.team.TeamView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetTeamController {

  private final GetTeam action;

  public GetTeamController(GetTeam action) {
    this.action = action;
  }

  @GetMapping("/get-team")
  public GetTeamResponse getTeam(@RequestParam Long id) throws Exception {
    TeamView team = this.action.execute(id);
    return new GetTeamResponse(team);
  }

  public record GetTeamResponse(TeamView team) {}
}
