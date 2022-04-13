package codurance.academyfinalboy.backend.model.team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Long createTeam(Long userId, String name, String description) {
        Team team = new Team(name, description, userId);
        return teamRepository.save(team);
    }

    public List<Team> getTeamsForUser(Long userId) {
        throw new UnsupportedOperationException();
    }
}
