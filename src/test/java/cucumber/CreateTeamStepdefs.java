package cucumber;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import codurance.academyfinalboy.backend.model.team.Team;
import codurance.academyfinalboy.backend.model.team.TeamRepository;
import codurance.academyfinalboy.backend.model.team.UserRef;
import codurance.academyfinalboy.backend.model.user.TeamRef;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateTeamStepdefs {

  @Autowired UserRepository userRepository;
  @Autowired TeamRepository teamRepository;
  private User savedUser;
  private Response response;
  private Long teamId;

  @When("the create team endpoint is called with:")
  public void theCreateTeamEndpointIsCalledWith(Map<String, String> data) {
    var requestBody =
        new CreateTeamRequest(new Team(data.get("teamName"), data.get("teamDescription")));

    response =
        given().contentType("application/json").body(requestBody).when().post("/create-team");
  }

  @Then("the team is created in the db with:")
  public void theTeamIsCreatedInTheDbWith(Map<String, String> data) {
    response.then().assertThat().statusCode(201).body("teamId", notNullValue());

    savedUser = userRepository.findByExternalId(data.get("externalId")).orElseThrow();

    teamId = response.jsonPath().getLong("teamId");
    Team team = teamRepository.findById(teamId).orElseThrow();
    assertThat(team.getId()).isEqualTo(teamId);
    assertThat(team.getName()).isEqualTo(data.get("teamName"));
    assertThat(team.getDescription()).isEqualTo(data.get("teamDescription"));
    assertThat(team.getMembers()).contains(new UserRef(savedUser.getId()));

    TeamWorld.storedTeam = team;
  }

  @And("the team is added on the user")
  public void theTeamIsAddedOnTheUser() {
    assertThat(savedUser.getTeams()).contains(new TeamRef(teamId));
  }

  private record CreateTeamRequest(Team team) {}
}
