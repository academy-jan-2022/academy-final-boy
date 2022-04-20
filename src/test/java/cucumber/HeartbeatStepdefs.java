package cucumber;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class HeartbeatStepdefs {

  private Response response;

  @When("Heartbeat api is called")
  public void heartbeatApiIsCalled() {
    response = get("/actuator/health");
  }

  @Then("the status is up")
  public void theStatusIsUp() {
    response.then().statusCode(200).body("status", equalTo("UP"));
  }
}
