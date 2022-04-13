package codurance.academyfinalboy.backend.infrastructure.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import codurance.academyfinalboy.backend.actions.Login;
import codurance.academyfinalboy.backend.configurations.AuthenticatedUser;
import codurance.academyfinalboy.backend.model.user.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

class GoogleTokenValidatorShould {
  String validToken = "";
  String invalidToken = "invalid_token";

  @Disabled("Do not have a permanent valid token, test must be run manually")
  @Test
  void return_true_when_token_is_valid() {
    AuthenticatedUser authenticatedUser = new AuthenticatedUser();
    GoogleTokenValidator googleTokenValidator = new GoogleTokenValidator(authenticatedUser, new RestTemplate());
    assertThat(googleTokenValidator.authenticateToken(validToken)).isTrue();
    assertThat(authenticatedUser.getExternalId()).isNotNull();
  }

  @Test
  void return_false_when_token_is_invalid() {
    AuthenticatedUser authenticatedUser = new AuthenticatedUser();
    GoogleTokenValidator googleTokenValidator = new GoogleTokenValidator(authenticatedUser, new RestTemplate());
    assertThat(googleTokenValidator.authenticateToken(invalidToken)).isFalse();
  }

  @Test
  void control_null_return_values_from_restTemplate() {

    RestTemplate restTemplate = mock(RestTemplate.class);

    when(restTemplate.getForObject(anyString(), any())).thenReturn(null);

    AuthenticatedUser authenticatedUser = new AuthenticatedUser();
    GoogleTokenValidator googleTokenValidator = new GoogleTokenValidator(authenticatedUser, restTemplate);
    assertThat(googleTokenValidator.authenticateToken(invalidToken)).isFalse();
  }
}
