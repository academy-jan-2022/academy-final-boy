package codurance.academyfinalboy.backend.infrastructure.services;

import static org.assertj.core.api.Assertions.assertThat;

import codurance.academyfinalboy.backend.configurations.AuthenticatedUser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class GoogleTokenValidatorShould {
  String validToken = "";
  String invalidToken = "invalid_token";

  @Disabled("Do not have a permanent valid token, test must be run manually")
  @Test
  void return_true_when_token_is_valid() {
    AuthenticatedUser authenticatedUser = new AuthenticatedUser();
    GoogleTokenValidator googleTokenValidator = new GoogleTokenValidator(authenticatedUser);
    assertThat(googleTokenValidator.authenticateToken(validToken)).isTrue();
    assertThat(authenticatedUser.getExternalId()).isNotNull();
  }

  @Test
  void return_false_when_token_is_invalid() {
    AuthenticatedUser authenticatedUser = new AuthenticatedUser();
    GoogleTokenValidator googleTokenValidator = new GoogleTokenValidator(authenticatedUser);
    assertThat(googleTokenValidator.authenticateToken(invalidToken)).isFalse();
  }
}
