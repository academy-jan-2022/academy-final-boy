package codurance.academyfinalboy.backend.model.user;

import java.util.Optional;

import codurance.academyfinalboy.backend.configurations.AuthenticatedUser;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final AuthenticatedUser authenticatedUser;

  public UserService(UserRepository userRepository, AuthenticatedUser authenticatedUser) {
    this.userRepository = userRepository;
    this.authenticatedUser = authenticatedUser;
  }

  public void createUser(String externalId, String fullName) {
    Optional<User> foundUser = userRepository.findByExternalId(externalId);

    if (foundUser.isEmpty()) {
      var user = new User(externalId, fullName);
      userRepository.save(user);
    }
  }

  public Optional<User> getCurrentUser() {
    var externalId = authenticatedUser.getExternalId();
    return userRepository.findByExternalId(externalId);
  }

  public void addTeamToUser(User user, Long teamId) {
    user.addTeam(teamId);
    userRepository.save(user);
  }
}
