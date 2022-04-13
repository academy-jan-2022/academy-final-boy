package codurance.academyfinalboy.backend.configurations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AuthenticateUserShould {

  @Test
  void get_current_externalId() {
    AuthenticatedUser authenticatedUser = new AuthenticatedUser();
    authenticatedUser.setExternalId("myID");
    assertEquals("myID",authenticatedUser.getExternalId());
  }
}
