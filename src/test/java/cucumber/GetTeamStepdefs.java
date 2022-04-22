package cucumber;

import codurance.academyfinalboy.backend.model.team.Team;
import codurance.academyfinalboy.backend.model.team.TeamRepository;
import codurance.academyfinalboy.backend.model.team.TeamView;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserRepository;
import codurance.academyfinalboy.backend.web.controllers.GetTeamController;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetTeamStepdefs {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TeamRepository teamRepository;

    private Team savedTeam;
    private Long savedTeamId;
    private User teamMember;
    private Response response;

    @And("the user is part of a team")
    public void theUsersArePartOfATeam(Map<String, String> data) {
        User user = userRepository.findByExternalId(data.get("externalId")).orElseThrow();
        Team team = new Team(data.get("teamName"), data.get("teamDescription"), user.getId());

        Long teamId = teamRepository.save(team);

        savedTeam = team;
        savedTeamId = teamId;
        teamMember = user;

        savedTeam.setId(teamId);
    }

    @When("the get team endpoint is called with the team id")
    public void theGetTeamEndpointIsCalledWithTheTeamId() {
        String path = "get-team/" + savedTeamId;

        response =
                given().contentType("application/json").when().get(path);
    }

    @Then("the team is returned from the db with the members included")
    public void theTeamIsReturnedFromTheDbWithTheMembersIncluded() {
        TeamView expectedTeam = new TeamView(savedTeam, List.of(teamMember));
        GetTeamController.GetTeamResponse expectedResponse = new GetTeamController.GetTeamResponse(expectedTeam);


        response.then().assertThat()
                .statusCode(200)
                .body("team", equalTo(expectedResponse.team()));
    }

    @When("the team endpoint is called with a non existing team id")
    public void theTeamEndpointIsCalledWithANonExistingTeamId() {
        String path = "get-team/33";

        response =
                given().contentType("application/json").when().get(path);
    }

    @Then("no team is returned from the database")
    public void noTeamIsReturnedFromTheDatabase() {
        GetTeamController.GetTeamResponse expectedResponse = new GetTeamController.GetTeamResponse(null);

        response.then().assertThat()
                .statusCode(204)
                .body("team", equalTo(expectedResponse.team()));
    }
}
