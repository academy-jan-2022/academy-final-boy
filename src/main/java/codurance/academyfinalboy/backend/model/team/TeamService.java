package codurance.academyfinalboy.backend.model.team;

import codurance.academyfinalboy.backend.model.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
  private final TeamRepository teamRepository;
  private final UserService userService;

  public TeamService(TeamRepository teamRepository, UserService userService) {
    this.teamRepository = teamRepository;
    this.userService = userService;
  }

  public Long createTeam(Long userId, String name, String description) {
    Team team = new Team(name, description, userId);
    return teamRepository.save(team);
  }

  public TeamView getTeam(Long teamId) {
    throw new UnsupportedOperationException();


  }
}
