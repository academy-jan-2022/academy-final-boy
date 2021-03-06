package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.user.UserService;
import org.springframework.stereotype.Component;

@Component
public class Login {

  private final UserService userService;

  public Login(UserService userService) {
    this.userService = userService;
  }

  public void execute(String externalId, String fullName) {
    userService.createUser(externalId, fullName);
  }
}
