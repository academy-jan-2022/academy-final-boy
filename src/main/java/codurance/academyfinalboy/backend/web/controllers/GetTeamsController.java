package codurance.academyfinalboy.backend.web.controllers;

import codurance.academyfinalboy.backend.actions.GetTeams;
import codurance.academyfinalboy.backend.model.team.Team;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
public class GetTeamsController {

  private GetTeams getTeams;

  public GetTeamsController(GetTeams getTeams) {
    this.getTeams = getTeams;
  }

  @GetMapping("/teams")
  public List<Team> getTeams() {
    return getTeams.execute();
  }
}
