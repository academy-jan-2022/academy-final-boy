package codurance.academyfinalboy.backend.web.controllers;

import static codurance.academyfinalboy.backend.web.controllers.LoginController.LoginRequest;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import codurance.academyfinalboy.backend.BaseSpringTest;
import codurance.academyfinalboy.backend.actions.Login;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.applicationinsights.TelemetryClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

class LoginControllerShould extends BaseSpringTest {
  @Autowired MockMvc mockMvc;

  @Autowired ObjectMapper mapper;

  @MockBean Login login;
  @MockBean TelemetryClient telemetryClient;

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

  @Test
  void publish_exception_on_error() throws Exception {
    var loginRequest = new LoginRequest("1561560156610", "fullName");
    IllegalStateException exception = new IllegalStateException("tested exception");
    doThrow(exception).when(login).execute(anyString(), anyString());

    mockMvc
        .perform(
            post("/login")
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(loginRequest)))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.message").value(exception.getMessage()));

    verify(telemetryClient).trackException(exception);
  }
}
