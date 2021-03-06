package codurance.academyfinalboy.backend.web.controllers;

import codurance.academyfinalboy.backend.actions.Login;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  private final Login login;

  public LoginController(Login login) {
    this.login = login;
  }

  @PostMapping("/login")
  void login(@RequestBody LoginRequest loginRequest) {
    login.execute(loginRequest.externalId(), loginRequest.fullName());
  }

  record LoginRequest(String externalId, String fullName) {}
}
