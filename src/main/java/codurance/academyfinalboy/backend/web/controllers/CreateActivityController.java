package codurance.academyfinalboy.backend.web.controllers;

import codurance.academyfinalboy.backend.actions.CreateActivity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CreateActivityController {
  private final CreateActivity createActivity;

  public CreateActivityController(CreateActivity createActivity) {
    this.createActivity = createActivity;
  }

  @PostMapping("/create-activity")
  void createActivity(@RequestBody CreateActivityRequest request){
    createActivity.execute(request);
  }


  public record CreateActivityRequest(
          long teamId, String activityName, int numberOfGroups, List<ActivityMemberRequest> members) {}

  public record ActivityMemberRequest(long id, String fullName) {}
}
