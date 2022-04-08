package cucumber;

import codurance.academyfinalboy.backend.user.UserRepository;
import io.cucumber.java.en.Given;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.beans.factory.annotation.Autowired;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "cucumber")
public class DatabaseStepdefs {

    @Autowired
    UserRepository userRepository;

    @Given("database is clean")
    public void databaseIsClean() {
        userRepository.clear();

    }

}
