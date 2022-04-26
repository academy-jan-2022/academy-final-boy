package codurance.academyfinalboy.backend.web.controllers;

import codurance.academyfinalboy.backend.BaseSpringTest;
import codurance.academyfinalboy.backend.actions.GetTeam;
import codurance.academyfinalboy.backend.actions.JoinTeam;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class JoinTeamControllerShould extends BaseSpringTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    JoinTeam joinTeamAction;

    @Test void
    call_the_join_team_action() throws Exception {

        UUID joinTokenId = UUID.randomUUID();
        JoinTeamController.JoinTeamRequest request = new JoinTeamController.JoinTeamRequest(joinTokenId);
        mockMvc
                .perform(
                        post("/join-team")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)));

        verify(joinTeamAction).execute(joinTokenId);
    }

}
