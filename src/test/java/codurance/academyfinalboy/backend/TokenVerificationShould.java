package codurance.academyfinalboy.backend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TokenVerificationController.class)
class TokenVerificationShould {

  @Autowired MockMvc mockMvc;

  @Test
  @Disabled("Do not have a permanent valid token, test must be run manually")
  void authorized_calls_with_a_valid_token_return_a_200() throws Exception {
    String validToken = "[enter valid token]";

    mockMvc
        .perform(get("/tokenvalidator").header("Authorization", validToken))
        .andExpect(status().isOk());
  }

  @Test
  void unauthorized_calls_with_a_missing_header_authorization() throws Exception {
    mockMvc.perform(get("/tokenvalidator")).andExpect(status().isUnauthorized());
  }

  @Test
  void unauthorized_calls_with_a_empty_header_authorization() throws Exception {
    String emptyToken = "";

    mockMvc
        .perform(get("/tokenvalidator").header("Authorization", emptyToken))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void unauthorized_calls_with_a_invalid_header_authorization() throws Exception {
    String invalidToken = "";

    mockMvc
        .perform(get("/tokenvalidator").header("Authorization", invalidToken))
        .andExpect(status().isUnauthorized());
  }
}
