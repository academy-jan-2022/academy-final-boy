package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.team.TeamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GetTeamShould {
    @Mock private TeamService mockedTeamService;

    @Test
    void call_get_team_team_service() throws Exception {
        GetTeam action = new GetTeam(mockedTeamService);
        Long teamId = 12L;

        action.execute(teamId);

        verify(mockedTeamService).getTeam(teamId);
    }
}
