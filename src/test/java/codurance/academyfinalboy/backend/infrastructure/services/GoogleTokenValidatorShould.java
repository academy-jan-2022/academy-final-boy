package codurance.academyfinalboy.backend.infrastructure.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class GoogleTokenValidatorShould {
  String validToken = "";
  String invalidToken = "invalid_token";

  @Disabled("Do not have a permanent valid token, test must be run manually")
  @Test
  void return_true_when_token_is_valid()
      throws IOException, InterruptedException, URISyntaxException {
    GoogleTokenValidator googleTokenValidator = new GoogleTokenValidator();
    assertTrue(googleTokenValidator.authenticateToken(validToken));
  }

  @Test
  void return_false_when_token_is_invalid() throws Exception {
    GoogleTokenValidator googleTokenValidator = new GoogleTokenValidator();
    assertFalse(googleTokenValidator.authenticateToken(invalidToken));
  }
}
