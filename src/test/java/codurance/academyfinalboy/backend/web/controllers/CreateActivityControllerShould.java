package codurance.academyfinalboy.backend.web.controllers;

import codurance.academyfinalboy.backend.BaseSpringTest;
import codurance.academyfinalboy.backend.actions.CreateActivity;
import codurance.academyfinalboy.backend.web.controllers.CreateActivityController.CreateActivityRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CreateActivityControllerShould extends BaseSpringTest {

  @Autowired MockMvc mockMvc;
  @Autowired ObjectMapper objectMapper;
  @MockBean CreateActivity createActivity;

  @Test
  void call_create_activity_action() throws Exception {
    var request =
        new CreateActivityRequest(1L, "activityName", 2, List.of());

    mockMvc
        .perform(
            post("/create-activity")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk());

    verify(createActivity).execute(request);
  }
}
