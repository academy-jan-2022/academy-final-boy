package codurance.academyfinalboy.backend.web.controllers;

import static codurance.academyfinalboy.backend.web.controllers.LoginController.LoginRequest;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import codurance.academyfinalboy.backend.BaseSpringTest;
import codurance.academyfinalboy.backend.actions.Login;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

class LoginControllerShould extends BaseSpringTest {
  @Autowired MockMvc mockMvc;

  @Autowired ObjectMapper mapper;

  @MockBean Login login;

  @Test
  void call_login_action() throws Exception {
    var loginRequest = new LoginRequest("1561560156610", "fullName");

    mockMvc
        .perform(
            post("/login")
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(loginRequest)))
        .andExpect(status().isOk());

    verify(login).execute(loginRequest.externalId(), loginRequest.fullName());
  }
}
