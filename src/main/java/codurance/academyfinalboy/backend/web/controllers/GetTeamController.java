package codurance.academyfinalboy.backend.web.controllers;

import codurance.academyfinalboy.backend.model.team.TeamView;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetTeamController {
    public record GetTeamResponse(TeamView team) {}
}
