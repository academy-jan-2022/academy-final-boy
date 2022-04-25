package cucumber;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginStepdefs {

  @Autowired UserRepository userRepository;
  private User savedUser;
  private Response response;

  @Given("the following users exist:")
  public void theFollowingUsersExist(List<Map<String, String>> data ) {
    data.forEach(this::theLoginEndpointIsCalledWith);
  }

  @When("the login endpoint is called with:")
  public void theLoginEndpointIsCalledWith(Map<String, String> data) {

    var requestBody = new LoginRequest(data.get("externalId"), data.get("fullName"));

    response = given().contentType("application/json").body(requestBody).when().post("/login");
  }

  @Then("the user is created in the db with:")
  public void theUserIsCreatedInTheDbWith(Map<String, String> data) {
    response.then().statusCode(200);

    String externalId = data.get("externalId");

    User user = userRepository.findByExternalId(externalId).orElseThrow();
    assertThat(user.getExternalId()).isEqualTo(externalId);
    assertThat(user.getUsername()).isEqualTo(data.get("username"));
    assertThat(user.getFullName()).isEqualTo(data.get("fullName"));
    assertThat(user.getId()).isNotNull();
  }

  @Given("the following user exists:")
  public void theFollowingUserExists(Map<String, String> data) {

    String externalId = data.get("externalId");
    User user = new User(externalId, data.get("fullName"));

    savedUser = userRepository.save(user);
  }

  @Then("the user is not created")
  public void theUserIsNotCreated() {
    response.then().statusCode(200);

    User user = userRepository.findByExternalId(savedUser.getExternalId()).orElseThrow();

    assertThat(user.getId()).isEqualTo(savedUser.getId());
  }


  private record LoginRequest(String externalId, String fullName) {}
}
