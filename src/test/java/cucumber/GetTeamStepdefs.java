package cucumber;

import codurance.academyfinalboy.backend.model.team.Team;
import codurance.academyfinalboy.backend.model.team.TeamRepository;
import codurance.academyfinalboy.backend.model.team.TeamView;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserRepository;
import codurance.academyfinalboy.backend.web.controllers.GetTeamController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetTeamStepdefs {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    ObjectMapper objectMapper;

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
        String path = "get-team?id=" + savedTeamId;

        response =
                given().contentType("application/json").when().get(path);
    }

    @Then("the team is returned from the db with the members included")
    public void theTeamIsReturnedFromTheDbWithTheMembersIncluded() throws JsonProcessingException {
        TeamView expectedTeam = new TeamView(savedTeam, List.of(teamMember));
        GetTeamController.GetTeamResponse expectedResponse = new GetTeamController.GetTeamResponse(expectedTeam);

        var expectedJson = objectMapper.writeValueAsString(expectedResponse);

        var actualResponse = response.then()
                .assertThat()
                .statusCode(200)
                .extract()
                .asString();

        assertEquals(actualResponse, expectedJson);
    }

    @When("the team endpoint is called with a non existing team id")
    public void theTeamEndpointIsCalledWithANonExistingTeamId() {
        String path = "get-team?id=33465";

        response =
                given().contentType("application/json").when().get(path);
    }

    @Then("no team is returned from the database")
    public void noTeamIsReturnedFromTheDatabase() throws JsonProcessingException {
        GetTeamController.GetTeamResponse expectedResponse = new GetTeamController.GetTeamResponse(null);

        var expectedJson = objectMapper.writeValueAsString(expectedResponse);

        var actualResponse = response.then()
                .assertThat()
                .statusCode(200)
                .extract()
                .asString();

        assertEquals(actualResponse, expectedJson);
    }
}
