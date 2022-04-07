package codurance.academyfinalboy.backend.user;

import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class LoginAction {

  private final UserService userService;

  public LoginAction(UserService userService) {
    this.userService = userService;
  }

  public void execute(UUID externalId, String fullName) {
    userService.createUser(externalId, fullName);
  }
}
