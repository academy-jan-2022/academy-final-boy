package codurance.academyfinalboy.backend.web.controllers;

import codurance.academyfinalboy.backend.actions.Login;
import com.microsoft.applicationinsights.TelemetryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  private final Login login;
  private final TelemetryClient telemetryClient;

  public LoginController(Login login, TelemetryClient telemetryClient) {
    this.login = login;
    this.telemetryClient = telemetryClient;
  }

  @PostMapping("/login")
  private ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

    try {
      login.execute(loginRequest.externalId(), loginRequest.fullName());
      telemetryClient.trackEvent("user logged in successfully");
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      telemetryClient.trackException(e);
      return ResponseEntity.badRequest().build();
    }
  }

  record LoginRequest(String externalId, String fullName) {}
}
