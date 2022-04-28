package codurance.academyfinalboy.backend.web.controllers;

import codurance.academyfinalboy.backend.actions.JoinTeam;
import java.util.UUID;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinTeamController {

  private final JoinTeam joinTeam;

  public JoinTeamController(JoinTeam joinTeam) {
    this.joinTeam = joinTeam;
  }

  @PostMapping("/join-team")
  public JoinTeamResponse joinTeam(@RequestBody JoinTeamRequest request) throws Exception {
    Long teamId = joinTeam.execute(request.joinTokenId());

    return new JoinTeamResponse(teamId);
  }

  public record JoinTeamRequest(UUID joinTokenId) {}

  public record JoinTeamResponse(Long teamId) {}
}
