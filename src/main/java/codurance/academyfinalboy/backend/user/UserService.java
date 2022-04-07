package codurance.academyfinalboy.backend.user;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  private static Character getFirstLetter(String name) {
    return name.charAt(0);
  }

  public void createUser(UUID externalId, String fullName) {
    Optional<Participant> foundUser = userRepository.findByExternalId(externalId);

    if (foundUser.isEmpty()) {
      var user = new Participant(externalId, fullName, createUsername(fullName));
      userRepository.save(user);
    }
  }

  private String createUsername(String fullName) {
    StringBuilder username = new StringBuilder();
    String[] names = fullName.split(" ");
    username.append(names[0]);

    Arrays.stream(names).skip(1).map(UserService::getFirstLetter).forEach(username::append);

    return username.toString();
  }
}
