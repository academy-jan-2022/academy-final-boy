package cucumber;

import codurance.academyfinalboy.backend.model.team.Team;
import codurance.academyfinalboy.backend.model.team.TeamRepository;
import codurance.academyfinalboy.backend.model.team.UserRef;
import codurance.academyfinalboy.backend.model.token.TimeProvider;
import codurance.academyfinalboy.backend.model.token.Token;
import codurance.academyfinalboy.backend.model.token.TokenIdProvider;
import codurance.academyfinalboy.backend.model.token.TokenRepository;
import codurance.academyfinalboy.backend.model.user.TeamRef;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserService;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;


public class JoinTeamStepdefs {

    @Autowired private TeamRepository teamRepository;
    @Autowired private TokenRepository tokenRepository;
    @Autowired private UserService userService;

    private Long teamId;
    private Response response;
    private UUID joinId;

    @And("the following team exists:")
    public void theFollowingTeamExists(Map<String, String> data){
        Team team = new Team(data.get("teamName"), data.get("teamDescription"), 999L);

        teamId = teamRepository.save(team);
    }

    @And("the token exists:")
    public void theTokenExists(Map<String, String> data){
        LocalDateTime expiryDate = new TimeProvider().getCurrentTime().plusMinutes(5);
        joinId = UUID. fromString(data.get("joinId"));

        tokenRepository.save(new Token(teamId, joinId, expiryDate));
    }

    @When("the join team endpoint is called with the join token id")
    public void theJoinTeamEndpointIsCalledWithTheJoinTokenId(){
        var requestBody = new JoinTeamRequest(joinId);
        response = given().contentType("application/json").body(requestBody).when().post("/join-team");

    }

    @Then("the user should be part of the team")
    public void theUserIsAddedToTheTeam(){
        User currentUser = userService.getCurrentUser().orElseThrow();
        Team currentTeam = teamRepository.findById(teamId).orElseThrow();

        UserRef currentUserRef = new UserRef(currentUser.getId());
        TeamRef currentTeamRef = new TeamRef(currentTeam.getId());

        assertThat(currentTeam.getMembers(), contains(currentUserRef));
        assertThat(currentUser.getTeams(), contains(currentTeamRef));
    }

    @And("the team id is returned")
    public void theTeamIdIsReturned(){
        Long teamIdInResponse = response.jsonPath().getLong("teamId");

        assertThat(teamIdInResponse, equalTo(teamId));
    }

    private record JoinTeamRequest(UUID joinTokenId) {
    }
}
