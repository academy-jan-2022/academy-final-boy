package codurance.academyfinalboy.backend.web.controllers;

import codurance.academyfinalboy.backend.BaseSpringTest;
import codurance.academyfinalboy.backend.actions.CreateTeam;
import codurance.academyfinalboy.backend.web.controllers.CreateTeamController.CreateTeamRequest;
import codurance.academyfinalboy.backend.web.controllers.CreateTeamController.CreateTeamRequest.TeamRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateTeamControllerShould extends BaseSpringTest {
  @Autowired MockMvc mockMvc;
  @Autowired ObjectMapper objectMapper;
  @MockBean CreateTeam createTeam;

  @Test
  void return_team_id() throws Exception {
    var request = new CreateTeamRequest(new TeamRequest("team name", "team description"));
    when(createTeam.execute(any(), any())).thenReturn(1L);

    mockMvc
        .perform(
            post("/create-team")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.teamId").value(1));
  }

  @Test
  void call_create_team_action() throws Exception {
    var request = new CreateTeamRequest(new TeamRequest("team name", "team description"));
    when(createTeam.execute(any(), any())).thenReturn(1L);

    mockMvc.perform(
        post("/create-team")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)));

    verify(createTeam).execute(request.team().name(), request.team().description());
  }
}
