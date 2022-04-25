package cucumber;

import codurance.academyfinalboy.backend.model.team.TeamRepository;
import cucumber.worlds.TeamWorld;
import cucumber.worlds.UserWorld;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateActivityStepdefs {

  @Autowired TeamRepository teamRepository;
  private Response response;

  @When("the activity is created with the users and the following information:")
  public void theActivityIsCreatedWithTheUsersAndTheFollowingInformation(
      Map<String, String> activityData) {
    var allUsers = UserWorld.storedUsers;

    var activityMembers =
        allUsers.stream()
            .map(user -> new ActivityMemberRequest(user.getId(), user.getFullName()))
            .toList();

    var requestBody =
        new CreateActivityRequest(
            TeamWorld.storedTeam.getId(),
            activityData.get("activityName"),
            Integer.parseInt(activityData.get("numberOfGroups")),
            activityMembers);

    response =
        given().contentType("application/json").body(requestBody).when().post("/create-activity");
  }

  @Then("the activity gets added to the team")
  public void theActivityGetsAddedToTheTeam() {
    var team = teamRepository.findById(TeamWorld.storedTeam.getId()).orElseThrow();

    assertThat(team.getActivities()).hasSize(1);
    response.then().statusCode(200);
  }

  private record CreateActivityRequest(
      long teamId, String activityName, int numberOfGroups, List<ActivityMemberRequest> members) {}

  private record ActivityMemberRequest(long userId, String fullName) {}
}
