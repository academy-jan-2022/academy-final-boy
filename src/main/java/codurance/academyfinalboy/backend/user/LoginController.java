package codurance.academyfinalboy.backend.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  private final LoginAction loginAction;

  public LoginController(LoginAction loginAction) {
    this.loginAction = loginAction;
  }

  @PostMapping("/login")
  private void login(@RequestBody LoginRequest loginRequest) {
    loginAction.execute(loginRequest.externalId(), loginRequest.fullName());
  }
}
