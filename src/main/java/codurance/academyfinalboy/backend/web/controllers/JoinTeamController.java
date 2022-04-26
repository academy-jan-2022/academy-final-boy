package codurance.academyfinalboy.backend.web.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class JoinTeamController {
  public record JoinTeamRequest(UUID joinTokenId) {

  }
}
