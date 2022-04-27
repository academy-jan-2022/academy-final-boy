package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class JoinTeamShould {

    @Mock
    private UserService mockedUserService;

    @Test void
    get_the_current_user() {
        JoinTeam joinTeamAction = new JoinTeam(mockedUserService);
        UUID joinTokenId = UUID.randomUUID();

        joinTeamAction.execute(joinTokenId);

        verify(mockedUserService).getCurrentUser();
    }

}
