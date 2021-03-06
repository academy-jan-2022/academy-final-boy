package codurance.academyfinalboy.backend.web.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import codurance.academyfinalboy.backend.actions.GetTeams;
import codurance.academyfinalboy.backend.configurations.InterceptorConfiguration;
import codurance.academyfinalboy.backend.infrastructure.services.GoogleTokenValidator;
import codurance.academyfinalboy.backend.model.team.Team;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.applicationinsights.TelemetryClient;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(GetTeamsController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class GetTeamsControllerShould {

  @Autowired MockMvc mockMvc;
  @Autowired ObjectMapper objectMapper;
  @MockBean GetTeams getTeams;

  @MockBean InterceptorConfiguration interceptorConfiguration;
  @MockBean GoogleTokenValidator googleTokenValidator;
  @MockBean TelemetryClient telemetryClient;

  @Test
  void get_a_list_of_teams_on_request() throws Exception {
    var expectedTeam1 = new Team("Team 1", "Team 1 description", 3L);
    var expectedTeam2 = new Team("Team 2", "Team 2 description", 3L);

    expectedTeam1.setId(11L);
    expectedTeam2.setId(14L);

    var expectedTeams =
        new GetTeamsController.GetTeamsResponse(List.of(expectedTeam1, expectedTeam2));
    ObjectMapper mapper = new ObjectMapper();
    String expectedJSON = mapper.writeValueAsString(expectedTeams);

    when(getTeams.execute()).thenReturn(expectedTeams.teams());

    mockMvc
        .perform(get("/teams").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJSON));
  }
}
