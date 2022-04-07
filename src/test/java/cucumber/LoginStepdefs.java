package cucumber;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import codurance.academyfinalboy.backend.user.User;
import codurance.academyfinalboy.backend.user.UserRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

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
  public void theUserIsCreatedInTheDbWith(List<Map<String, String>> data) {
    response.then().statusCode(200);

    data.forEach(
        row -> {
          UUID externalId = UUID.fromString(row.get("externalId"));

          User user = userRepository.findByExternalId(externalId);
          assertThat(user.getExternalId()).isEqualTo(externalId);
          assertThat(user.getUsername()).isEqualTo(row.get("username"));
          assertThat(user.getFullName()).isEqualTo(row.get("fullName"));
          assertThat(user.getId()).isNotNull();
        });
  }

  @Given("the following user exists:")
  public void theFollowingUserExists(List<Map<String, String>> data) {
    data.forEach(
        row -> {
          UUID externalId = UUID.fromString(row.get("externalId"));
          User user = new User(externalId, row.get("fullName"), row.get("username"));

          savedUser = userRepository.save(user);
        });
  }

  @Then("the user is not created")
  public void theUserIsNotCreated() {
    response.then().statusCode(200);

    User user = userRepository.findByExternalId(savedUser.getExternalId());

    assertThat(user.getId()).isEqualTo(savedUser.getId());
  }
}
