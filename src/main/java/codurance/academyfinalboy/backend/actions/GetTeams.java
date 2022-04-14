package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.team.TeamRepository;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserService;

import java.util.Optional;

public class GetTeams {
    private  UserService userService;
    private TeamRepository teamRepository;

    public GetTeams(UserService userService, TeamRepository teamRepository) {
        this.userService = userService;
        this.teamRepository = teamRepository;
    }

    public void execute() {
        User currentUser = userService.getCurrentUser().orElseThrow();
        teamRepository.findAllById(currentUser.getTeams());

    }
}
