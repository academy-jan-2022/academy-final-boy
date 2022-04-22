package codurance.academyfinalboy.backend.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenVerificationController {

  @GetMapping("/tokenvalidator")
  public void isTokenValid() {
    //for testing purposes
  }
}
