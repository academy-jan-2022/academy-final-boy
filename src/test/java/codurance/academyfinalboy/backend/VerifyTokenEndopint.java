package codurance.academyfinalboy.backend;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TokenVerificationController.class)
class TokenVerificationEndpointShould {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void unauthorized_calls_without_a_valid_token_return_a_401_error() throws Exception {
    // Given
    String token = "";

    mockMvc.perform(post("/tokenvalidator")
              .content(token)
              .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized());
  }
}
