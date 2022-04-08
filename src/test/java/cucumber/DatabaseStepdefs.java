package cucumber;

import codurance.academyfinalboy.backend.model.user.UserRepository;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseStepdefs extends BaseCucumberTest {

  @Autowired UserRepository userRepository;

  @Given("database is clean")
  public void databaseIsClean() {
    userRepository.clear();
  }
}
