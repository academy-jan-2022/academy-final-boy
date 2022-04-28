package codurance.academyfinalboy.backend.web.controllers;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import codurance.academyfinalboy.backend.BaseSpringTest;
import codurance.academyfinalboy.backend.actions.RemoveUser;
import codurance.academyfinalboy.backend.model.team.Team;
import codurance.academyfinalboy.backend.model.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

class RemoveUserControllerShould extends BaseSpringTest {
  @Autowired MockMvc mockMvc;
  @Autowired ObjectMapper objectMapper;
  @MockBean RemoveUser removeUser;

  @Test
  void call_remove_user_action() throws Exception {
    Team expectedTeam = new Team("Team 1", "Team 1 description", 4L);
    User user = new User("externalId", "Full Name");

    user.setId(4L);
    expectedTeam.setId(11L);

    mockMvc
        .perform(delete("/remove-user?teamId=11").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    ;
    verify(removeUser).execute(expectedTeam.getId());
  }
}
