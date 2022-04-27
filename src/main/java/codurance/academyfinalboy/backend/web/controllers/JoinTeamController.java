package codurance.academyfinalboy.backend.web.controllers;


import codurance.academyfinalboy.backend.actions.JoinTeam;
import codurance.academyfinalboy.backend.model.token.InvalidTokenException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class JoinTeamController {

  private final JoinTeam joinTeam;

  public JoinTeamController(JoinTeam joinTeam) {
    this.joinTeam = joinTeam;
  }

  @PostMapping("/join-team")
  public void joinTeam(@RequestBody JoinTeamRequest request) throws InvalidTokenException {

    joinTeam.execute(request.joinTokenId());
  }

  public record JoinTeamRequest(UUID joinTokenId) {

  }
}
