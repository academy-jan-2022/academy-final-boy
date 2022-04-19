package codurance.academyfinalboy.backend.web.controllers;

import codurance.academyfinalboy.backend.actions.GetTeams;
import codurance.academyfinalboy.backend.model.team.Team;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class GetTeamsController {

    private GetTeams getTeams;

    public GetTeamsController(GetTeams getTeams) {
        this.getTeams = getTeams;
    }

    @GetMapping("/teams")
    public List<Team> getTeams(){
        return getTeams.execute();
    }


}
