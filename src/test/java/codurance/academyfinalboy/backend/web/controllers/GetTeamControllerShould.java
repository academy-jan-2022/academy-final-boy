package codurance.academyfinalboy.backend.web.controllers;


import codurance.academyfinalboy.backend.BaseSpringTest;
import codurance.academyfinalboy.backend.actions.GetTeam;
import codurance.academyfinalboy.backend.configurations.InterceptorConfiguration;
import codurance.academyfinalboy.backend.infrastructure.services.GoogleTokenValidator;
import codurance.academyfinalboy.backend.model.team.Team;
import codurance.academyfinalboy.backend.model.team.TeamView;
import codurance.academyfinalboy.backend.model.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.applicationinsights.TelemetryClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class GetTeamControllerShould extends BaseSpringTest {
    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @MockBean GetTeam getTeam;

    @MockBean InterceptorConfiguration interceptorConfiguration;
    @MockBean GoogleTokenValidator googleTokenValidator;
    @MockBean TelemetryClient telemetryClient;

    @Test
    void call_get_team_action() throws Exception {
        Team expectedTeam = new Team("Team 1", "Team 1 description", 4L);
        User user = new User("externalId", "Full Name");

        user.setId(4L);
        expectedTeam.setId(11L);

        TeamView team = new TeamView(expectedTeam, List.of(user));

        when(getTeam.execute()).thenReturn(team);

        mockMvc
            .perform(get("/get-team?id=11")
            .contentType(MediaType.APPLICATION_JSON));

        verify(getTeam).execute();
    }
}
