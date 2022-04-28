package cucumber;

import static org.assertj.core.api.Assertions.assertThat;

import codurance.academyfinalboy.backend.model.team.Team;
import codurance.academyfinalboy.backend.model.team.TeamRepository;
import codurance.academyfinalboy.backend.model.team.UserRef;
import codurance.academyfinalboy.backend.model.user.TeamRef;
import codurance.academyfinalboy.backend.model.user.User;
import cucumber.worlds.TeamWorld;
import cucumber.worlds.UserWorld;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

public class RemoveUserStepdefs {
  @Autowired TeamRepository teamRepository;
  private Response response;

  @When("the user requests to be removed from the team")
  public void theUserRequestsToBeRemovedFromTheTeam() {
    response = RestAssured.delete("/remove-user?teamId=" + TeamWorld.storedTeam.getId());
  }

  @Then("the user is no longer part of the team")
  public void theUserIsNoLongerPartOfTheTeam() {
    response.then().assertThat().statusCode(200);

    Team team = teamRepository.findById(TeamWorld.storedTeam.getId()).orElseThrow();
    User user = UserWorld.currentUser;

    assertThat(team.getMembers()).hasSize(1);
    assertThat(team.getMembers()).doesNotContain(new UserRef(UserWorld.currentUser.getId()));
    assertThat(user.getTeams()).doesNotContain(new TeamRef(TeamWorld.storedTeam.getId()));
  }
}
