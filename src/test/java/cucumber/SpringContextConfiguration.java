package cucumber;

import codurance.academyfinalboy.backend.BackendApplication;
import codurance.academyfinalboy.backend.model.user.UserRepository;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
    classes = BackendApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
@ActiveProfiles("test")
class SpringContextConfiguration {

  @Autowired UserRepository userRepository;

  @Before
  void tearDown() {
    userRepository.clear();
  }

  @BeforeAll
  void tearDown2() {
    userRepository.clear();
  }

  @BeforeEach
  void setUp() {
    userRepository.clear();
  }
}
