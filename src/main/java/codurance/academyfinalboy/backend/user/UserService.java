package codurance.academyfinalboy.backend.user;

import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {

    this.userRepository = userRepository;
  }

  public void createUser(UUID externalId, String fullName) {

    var user = new User(externalId, fullName, createUsername(fullName));

    userRepository.save(user);
  }

  private String createUsername(String fullName) {
    StringBuilder username = new StringBuilder();

    String[] strings = fullName.split(" ");

    for (int i = 0; i < strings.length; i++) {

      if (i == 0) {
        username.append(strings[i]);
      } else {
        username.append(strings[i].charAt(0));
      }
    }
    return username.toString();
  }
}
