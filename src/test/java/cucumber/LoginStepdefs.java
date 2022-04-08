package cucumber;

import codurance.academyfinalboy.backend.user.User;
import codurance.academyfinalboy.backend.user.UserRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Map;
import java.util.UUID;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "cucumber")
public class LoginStepdefs {

  @LocalServerPort private int port = 0;
  private User savedUser;
  private Response response;

  private record LoginRequest(UUID externalId, String fullName) {}

  @Autowired UserRepository userRepository;

  @When("the login endpoint is called with:")
  public void theLoginEndpointIsCalledWith(Map<String, String> data) {

    var requestBody =
        new LoginRequest(UUID.fromString(data.get("externalId")), data.get("fullName"));

    response =
        given().port(port).when().contentType("application/json").body(requestBody).post("/login");
  }

  @Then("the user is created in the db with:")
  public void theUserIsCreatedInTheDbWith(Map<String, String> data) {
    response.then().statusCode(200);

    UUID externalId = UUID.fromString(data.get("externalId"));

    User user = userRepository.findByExternalId(externalId).orElseThrow();
    assertThat(user.getExternalId()).isEqualTo(externalId);
    assertThat(user.getUsername()).isEqualTo(data.get("username"));
    assertThat(user.getFullName()).isEqualTo(data.get("fullName"));
    assertThat(user.getId()).isNotNull();
  }

  @Given("the following user exists:")
  public void theFollowingUserExists(Map<String, String> data) {

    UUID externalId = UUID.fromString(data.get("externalId"));
    User user = new User(externalId, data.get("fullName"));

    savedUser = userRepository.save(user);
  }

  @Then("the user is not created")
  public void theUserIsNotCreated() {
    response.then().statusCode(200);

    User user = userRepository.findByExternalId(savedUser.getExternalId()).orElseThrow();

    assertThat(user.getId()).isEqualTo(savedUser.getId());
  }
}
