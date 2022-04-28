package codurance.academyfinalboy.backend.web.controllers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import codurance.academyfinalboy.backend.BaseSpringTest;
import codurance.academyfinalboy.backend.actions.JoinTeam;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

class JoinTeamControllerShould extends BaseSpringTest {
  @Autowired MockMvc mockMvc;
  @Autowired ObjectMapper objectMapper;
  @MockBean JoinTeam joinTeamAction;

  @Test
  void call_the_join_team_action() throws Exception {

    UUID joinTokenId = UUID.randomUUID();
    JoinTeamController.JoinTeamRequest request =
        new JoinTeamController.JoinTeamRequest(joinTokenId);
    mockMvc.perform(
        post("/join-team")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)));
    verify(joinTeamAction).execute(joinTokenId);
  }

  @Test
  void return_a_join_team_response_with_team_id() throws Exception {
    Long teamId = 2L;
    JoinTeamController.JoinTeamResponse joinTeamResponse =
        new JoinTeamController.JoinTeamResponse(teamId);

    UUID joinTokenId = UUID.randomUUID();
    JoinTeamController.JoinTeamRequest request =
        new JoinTeamController.JoinTeamRequest(joinTokenId);

    when(joinTeamAction.execute(joinTokenId)).thenReturn(teamId);

    ObjectMapper objectMapper = new ObjectMapper();
    String expectedJSON = objectMapper.writeValueAsString(joinTeamResponse);

    mockMvc
        .perform(
            post("/join-team")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(content().json(expectedJSON));
  }
}
