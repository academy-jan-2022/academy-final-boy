package codurance.academyfinalboy.backend.user;

import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@ActiveProfiles("test")
class LoginControllerShould {

  @Autowired MockMvc mockMvc;

  @Autowired ObjectMapper mapper;

  @MockBean LoginAction loginAction;

  @Test
  void call_login_action() throws Exception {
    var loginRequest = new LoginRequest(UUID.randomUUID(), "fullName");

    mockMvc
        .perform(
            post("/login")
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(loginRequest)))
        .andExpect(status().isOk());

    verify(loginAction).execute(loginRequest.externalId(), loginRequest.fullName());
  }
}
