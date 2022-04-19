package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.team.Team;
import codurance.academyfinalboy.backend.model.team.TeamRepository;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserService;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GetTeams {
  private UserService userService;
  private TeamRepository teamRepository;

  public GetTeams(UserService userService, TeamRepository teamRepository) {
    this.userService = userService;
    this.teamRepository = teamRepository;
  }

  public List<Team> execute() {
    User currentUser = userService.getCurrentUser().orElseThrow();
    return teamRepository.findAllById(currentUser.getTeams());
  }
}
