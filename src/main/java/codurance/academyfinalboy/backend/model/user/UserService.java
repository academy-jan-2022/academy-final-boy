package codurance.academyfinalboy.backend.model.user;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void createUser(UUID externalId, String fullName) {
    Optional<User> foundUser = userRepository.findByExternalId(externalId);

    if (foundUser.isEmpty()) {
      var user = new User(externalId, fullName);
      userRepository.save(user);
    }
  }
}
