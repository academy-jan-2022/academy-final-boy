package cucumber;

import codurance.academyfinalboy.backend.model.team.Team;
import codurance.academyfinalboy.backend.model.team.TeamRepository;
import codurance.academyfinalboy.backend.model.user.TeamRef;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateTeamStepdefs {

  @Autowired UserRepository userRepository;
  @Autowired TeamRepository teamRepository;
  @LocalServerPort private int port = 0;
  private User savedUser;
  private Response response;
  private Long teamId;

  @When("the create team endpoint is called with:")
  public void theCreateTeamEndpointIsCalledWith(Map<String, String> data) {
    var requestBody =
        new CreateTeamRequest(new Team(data.get("teamName"), data.get("teamDescription")));
    response =
        given()
            .port(port)
            .when()
            .contentType("application/json")
            .body(requestBody)
            .post("/create-team");
  }

  @Then("the team is created in the db with:")
  public void theTeamIsCreatedInTheDbWith(Map<String, String> data) {
    var responseBody = response.body().as(CreateTeamResponse.class);
    teamId = responseBody.teamId();
    String externalId = data.get("externalId");
    savedUser = userRepository.findByExternalId(externalId).orElseThrow();

    Team team = teamRepository.findById(teamId).orElseThrow();
    assertThat(team.getId()).isEqualTo(teamId);
    assertThat(team.getName()).isEqualTo(data.get("teamName"));
    assertThat(team.getDescription()).isEqualTo(data.get("teamDescription"));
    assertThat(team.getMembers()).contains(savedUser.getId());
  }

  @And("the team is added on the user")
  public void theTeamIsAddedOnTheUser() {
    assertThat(savedUser.getTeams()).contains(new TeamRef(teamId));
  }

  private record CreateTeamRequest(Team team) {}

  private record CreateTeamResponse(Long teamId) {}
}
