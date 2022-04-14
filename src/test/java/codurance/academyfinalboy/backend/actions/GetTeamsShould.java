package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.user.UserService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GetTeamsShould {

    @Test
    public void get_current_user_from_user_service() {
        UserService mockedUserService = mock(UserService.class);
        GetTeams getTeams = new GetTeams(mockedUserService);

        getTeams.execute();

        verify(mockedUserService).getCurrentUser();
    }
}

