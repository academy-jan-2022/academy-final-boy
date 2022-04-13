package codurance.academyfinalboy.backend.infrastructure.services;

import codurance.academyfinalboy.backend.configurations.AuthenticatedUser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GoogleTokenValidatorShould {
  String validToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImYxMzM4Y2EyNjgzNTg2M2Y2NzE0MDhmNDE3MzhhN2I0OWU3NDBmYzAiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiYXpwIjoiNjcxMjA4NTQ4MjUzLWhyZDVhNG52cms0b3Zzc2Nmc2twcnNib2RuN2F0ZThrLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiYXVkIjoiNjcxMjA4NTQ4MjUzLWhyZDVhNG52cms0b3Zzc2Nmc2twcnNib2RuN2F0ZThrLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwic3ViIjoiMTAzNTU4NDUzMjk5NjE5MTE1MzYyIiwiaGQiOiJjb2R1cmFuY2UuY29tIiwiZW1haWwiOiJicmlhbi5oZC5ob2FuZ0Bjb2R1cmFuY2UuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImF0X2hhc2giOiJBamNiVGR4cFMyMkpVX085RFRKZE5BIiwibmFtZSI6IkJyaWFuIEhvYW5nIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hLS9BT2gxNEdpQ3VjNkxXV2Rqb3NwYV9uVmxSanRiWXlKcXJLUGxUVG96QnZTcz1zOTYtYyIsImdpdmVuX25hbWUiOiJCcmlhbiIsImZhbWlseV9uYW1lIjoiSG9hbmciLCJsb2NhbGUiOiJlbi1HQiIsImlhdCI6MTY0OTgzODc3MCwiZXhwIjoxNjQ5ODQyMzcwLCJqdGkiOiJiZDljYzU5ODNmNDg0YTc4YjRhYjZjMzEzMjUxYTNlZDQwMWNjNzU3In0.dzdFMSGj-xO6W2s1dZ-onCIubbavYB8XWPrDKK3RtgfNaoIS9GsIqdMgDfGVL6gEu-aDeZSIXX1yLFex46JXrS5CXZAHsorAFAxGnNwWmdYLiOT9RZIluJTBAEiuZ3gbzke4bJjCkEZJZ1N9LPm3UgICRKeDUsvPmGSTfR3QF2d_hi3jRYBD4lV8av7FpVfk_uqXLeCoKbo6U6hjz5QoHURXlxNY3N4t0a6wxCd_xaHHJRmXrof6ND_8jTQl80RB2SzlryujEYw6uD2PkczagMi9BpeH3Rx_h2PpJ6MUOQf8N3NHnmYwx32x4QCS89EmtCNjV5s0GfWvYqDZdrnJbg";
  String invalidToken = "invalid_token";

//  @Disabled("Do not have a permanent valid token, test must be run manually")
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
