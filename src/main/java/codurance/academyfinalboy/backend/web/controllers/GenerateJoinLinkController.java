package codurance.academyfinalboy.backend.web.controllers;

import static org.springframework.http.HttpStatus.CREATED;

import codurance.academyfinalboy.backend.actions.GenerateJoinLink;
import java.util.UUID;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerateJoinLinkController {

  private final GenerateJoinLink generateJoinLink;

  public GenerateJoinLinkController(GenerateJoinLink generateJoinLink) {
    this.generateJoinLink = generateJoinLink;
  }

  @PostMapping("/generate-join-link")
  @ResponseStatus(CREATED)
  public GenerateJoinLinkResponse generate(@RequestBody GenerateJoinLinkRequest request) {
    return new GenerateJoinLinkResponse(generateJoinLink.execute(request.teamId()));
  }

  public record GenerateJoinLinkRequest(long teamId) {}

  private record GenerateJoinLinkResponse(UUID token) {}
}
