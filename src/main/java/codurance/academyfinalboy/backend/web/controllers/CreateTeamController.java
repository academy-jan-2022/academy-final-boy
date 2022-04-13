package codurance.academyfinalboy.backend.web.controllers;

import codurance.academyfinalboy.backend.actions.CreateTeam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class CreateTeamController {

  private final CreateTeam createTeam;

  public CreateTeamController(CreateTeam createTeam) {
    this.createTeam = createTeam;
  }

  @PostMapping("/create-team")
  @ResponseStatus(CREATED)
  public TeamResponse createTeam(@RequestBody CreateTeamRequest request){
    return new TeamResponse(createTeam.execute(request.team().name(), request.team().description()));
  }

  public record CreateTeamRequest(TeamRequest team) {
    public record TeamRequest(String name, String description) {}
  }

  private record TeamResponse(Long teamId){}

}
