package cucumber;

import codurance.academyfinalboy.backend.model.team.Team;
import codurance.academyfinalboy.backend.model.team.TeamService;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetTeamsStepdefs {

    @Autowired TeamService teamService;
    @Autowired UserRepository userRepository;

    private Response response;
    Team expectedTeam1;
    Team expectedTeam2;

    @LocalServerPort private int port = 0;

    @And("the user is part of two teams")
    public void theUserIsPartOfTwoTeams (Map<String, String> data) {
        User user = userRepository.findByExternalId(data.get("externalId")).orElseThrow();

        expectedTeam1 = new Team(data.get("teamName1"), data.get("teamDescription1"), user.getId());
        expectedTeam2 = new Team(data.get("teamName2"), data.get("teamDescription2"), user.getId());

        Long team1Id = teamService.createTeam(user.getId(), data.get("teamName1"), data.get("teamDescription1"));
        Long team2Id = teamService.createTeam(user.getId(), data.get("teamName2"), data.get("teamDescription2"));

        expectedTeam1.setId(team1Id);
        expectedTeam2.setId(team2Id);
    }

    @When("the teams endpoint is called with:")
    public void theTeamsEndpointIsCalledWith (Map<String, String> data) {
        response =
                given()
                        .port(port)
                        .when()
                        .contentType("application/json")
                        .get("/teams");
    }

    @Then("the teams are returned from the database:")
    public void theTeamsAreReturnedFromTheDb () throws JsonProcessingException {

        List<Team> expectedTeams = new ArrayList<>(Arrays.asList(expectedTeam1, expectedTeam2));
        ObjectMapper mapper = new ObjectMapper();
        String expectedJSON = mapper.writeValueAsString(expectedTeams);

        var bodyContent = response.getBody().asString();

        assertEquals(expectedJSON, bodyContent);
    }

}
