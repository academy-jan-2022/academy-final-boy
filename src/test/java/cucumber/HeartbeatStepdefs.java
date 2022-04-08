package cucumber;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.boot.web.server.LocalServerPort;

public class HeartbeatStepdefs extends BaseCucumberTest {

  @LocalServerPort private int port = 0;

  private Response response;

  @When("Heartbeat api is called")
  public void heartbeatApiIsCalled() {
    response = given().port(port).when().get("/actuator/health");
  }

  @Then("the status is up")
  public void theStatusIsUp() {
    response.then().statusCode(200).body("status", equalTo("UP"));
  }
}
