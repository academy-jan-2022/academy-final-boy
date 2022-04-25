package cucumber.utils;

import codurance.academyfinalboy.backend.model.team.TeamRepository;
import codurance.academyfinalboy.backend.model.token.TokenRepository;
import codurance.academyfinalboy.backend.model.user.UserRepository;
import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class DbUtils {

  @Autowired UserRepository userRepository;
  @Autowired TeamRepository teamRepository;
  @Autowired TokenRepository tokenRepository;

  @Before
  public void databaseIsClean() {
    userRepository.clear();
    teamRepository.clear();
    tokenRepository.clear();
  }
}
