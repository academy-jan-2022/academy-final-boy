package codurance.academyfinalboy.backend.web.controllers;

import codurance.academyfinalboy.backend.actions.Login;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class LoginController {

  private final Login login;

  public LoginController(Login login) {
    this.login = login;
  }

  record LoginRequest(UUID externalId, String fullName) {}

  @PostMapping("/login")
  private void login(@RequestBody LoginRequest loginRequest) {
    login.execute(loginRequest.externalId(), loginRequest.fullName());
  }
}
