package cucumber;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.web.server.LocalServerPort;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "cucumber")
public class HeartbeatStepDefs {

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
