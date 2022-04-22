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
    void call_get_team_team_service(){
        GetTeam action = new GetTeam(mockedTeamService);

        action.execute();

        Long teamId = 12L;
        verify(mockedTeamService).getTeam(teamId);
    }
}
