package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.infrastructure.repositories.team.SpringDataJdbcTeamRepository;
import codurance.academyfinalboy.backend.model.team.TeamRepository;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class GetTeamsShould {

    @Test
    public void get_current_user_from_user_service() {
        UserService mockedUserService = mock(UserService.class);
        GetTeams getTeams = new GetTeams(mockedUserService);

        getTeams.execute();

        verify(mockedUserService).getCurrentUser();
    }

    @Test
    public void get_teams_from_team_repository() {
        UserService mockedUserService = mock(UserService.class);
        TeamRepository mockedTeamRepository = mock(TeamRepository.class);

        User currentUser = new User("1250", "Bob Jones");

        currentUser.addTeam(12L);
        currentUser.addTeam(13L);

        when(mockedUserService.getCurrentUser()).thenReturn(Optional.of(currentUser));

        GetTeams getTeams = new GetTeams(mockedUserService);
        getTeams.execute();

        verify(mockedTeamRepository).findAllById(currentUser.getTeams());
    }
}

