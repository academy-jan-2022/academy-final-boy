package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.team.Activity;
import codurance.academyfinalboy.backend.model.team.ActivityMember;
import codurance.academyfinalboy.backend.model.team.TeamService;
import codurance.academyfinalboy.backend.web.controllers.CreateActivityController;
import org.springframework.stereotype.Component;

@Component
public class CreateActivity {
  private final TeamService teamService;

  public CreateActivity(TeamService teamService) {
    this.teamService = teamService;
  }

  public void execute(CreateActivityController.CreateActivityRequest request) {
    var members =
        request.members().stream().map(member -> new ActivityMember(member.fullName())).toList();

    teamService.addActivity(
        request.teamId(), new Activity(request.activityName(), members, request.numberOfGroups()));
  }
}
