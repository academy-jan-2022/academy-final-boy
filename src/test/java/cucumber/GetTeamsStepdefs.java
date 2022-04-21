package cucumber;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import codurance.academyfinalboy.backend.model.team.Team;
import codurance.academyfinalboy.backend.model.team.TeamService;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserRepository;
import codurance.academyfinalboy.backend.web.controllers.GetTeamsController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

public class GetTeamsStepdefs {

  @Autowired TeamService teamService;
  @Autowired UserRepository userRepository;

  private Response response;
  private Team expectedTeam1;
  private Team expectedTeam2;

  @LocalServerPort private int port = 0;

  @And("the user is part of one team")
  public void theUserIsPartOfOneTeam(Map<String, String> data) {
    User user = userRepository.findByExternalId(data.get("externalId")).orElseThrow();

    expectedTeam1 = new Team(data.get("teamName"), data.get("teamDescription"), user.getId());

    Long team1Id =
        teamService.createTeam(user.getId(), data.get("teamName"), data.get("teamDescription"));

    teamService.createTeam(310L, "Boy", "We love cakes");

    expectedTeam1.setId(team1Id);
  }

  @And("the user is part of two teams")
  public void theUserIsPartOfTwoTeams(Map<String, String> data) {
    User user = userRepository.findByExternalId(data.get("externalId")).orElseThrow();

    expectedTeam1 = new Team(data.get("teamName1"), data.get("teamDescription1"), user.getId());
    expectedTeam2 = new Team(data.get("teamName2"), data.get("teamDescription2"), user.getId());

    Long team1Id =
        teamService.createTeam(user.getId(), data.get("teamName1"), data.get("teamDescription1"));
    Long team2Id =
        teamService.createTeam(user.getId(), data.get("teamName2"), data.get("teamDescription2"));

    expectedTeam1.setId(team1Id);
    expectedTeam2.setId(team2Id);
  }

  @When("the teams endpoint is called with current logged user:")
  public void theTeamsEndpointIsCalledWith() {
    response = given().port(port).when().contentType("application/json").get("/teams");
  }

  @Then("the teams are returned from the database:")
  public void theTeamsAreReturnedFromTheDb() throws JsonProcessingException {

    var expectedTeams =
        new GetTeamsController.GetTeamsResponse(List.of(expectedTeam1, expectedTeam2));
    ObjectMapper mapper = new ObjectMapper();
    String expectedJSON = mapper.writeValueAsString(expectedTeams);

    var bodyContent = response.getBody().asString();

    assertEquals(expectedJSON, bodyContent);
  }

  @Then("the team are returned from the database:")
  public void theTeamAreReturnedFromTheDb() throws JsonProcessingException {

    var expectedTeams = new GetTeamsController.GetTeamsResponse(List.of(expectedTeam1));
    ObjectMapper mapper = new ObjectMapper();
    String expectedJSON = mapper.writeValueAsString(expectedTeams);

    var bodyContent = response.getBody().asString();

    assertEquals(expectedJSON, bodyContent);
  }

  @Then("no teams are returned from the database:")
  public void noTeamsAreReturnedFromTheDatabase() throws JsonProcessingException {
    var expectedTeams = new GetTeamsController.GetTeamsResponse(new ArrayList<>());
    ObjectMapper mapper = new ObjectMapper();
    String expectedJSON = mapper.writeValueAsString(expectedTeams);

    var bodyContent = response.getBody().asString();

    assertEquals(expectedJSON, bodyContent);
  }
}
