package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.user.UserService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Login {

  private final UserService userService;

  public Login(UserService userService) {
    this.userService = userService;
  }

  public void execute(UUID externalId, String fullName) {
    userService.createUser(externalId, fullName);
  }
}
