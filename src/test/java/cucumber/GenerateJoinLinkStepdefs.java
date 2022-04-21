package cucumber;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class GenerateJoinLinkStepdefs {
    private Response response;

    @When("the generate join link endpoint is called")
    public void theGenerateJoinLinkEndpointIsCalled() {
        Long storedTeamId = TeamWorld.storedTeam.getId();
        var requestBody =
                new GenerateLinkRequest(storedTeamId);

        response =
                given().contentType("application/json").body(requestBody).when().post("/generate-join-link");
    }

    @Then("a link is generated")
    public void aLinkIsGenerated() {
        response.then().assertThat().statusCode(201).body("token", notNullValue());
    }

    public record GenerateLinkRequest(Long teamId) {}
}
