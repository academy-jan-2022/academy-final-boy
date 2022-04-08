package cucumber;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

public class LoginStepdefs extends BaseCucumberTest {

  @Autowired UserRepository userRepository;
  @LocalServerPort private int port = 0;
  private User savedUser;
  private Response response;

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

  private record LoginRequest(UUID externalId, String fullName) {}
}
