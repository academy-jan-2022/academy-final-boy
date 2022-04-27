package codurance.academyfinalboy.backend.builders;

import codurance.academyfinalboy.backend.model.team.Activity;
import codurance.academyfinalboy.backend.model.team.ActivityMember;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ActivityBuilder {
  private String name;
  private List<ActivityMember> members;
  private int numberOfGroups;

  public ActivityBuilder() {
    this.name = "fizzBuzz";
    members = generateActivityMembersBy(3);
    numberOfGroups = 2;
  }

  public Activity build() {
    Activity activity = new Activity(name, members, numberOfGroups);
    return activity;
  }

  public static List<ActivityMember> generateActivityMembersBy(int numberOfMembers) {
    return new ArrayList<>(
        IntStream.range(0, numberOfMembers)
            .mapToObj(index -> new ActivityMember(String.valueOf(index)))
            .toList());
  }

  public ActivityBuilder withMembers(int numberOfMembers) {
    members = generateActivityMembersBy(numberOfMembers);
    return this;
  }

  public ActivityBuilder withNumberOfGroups(int numberOfGroups) {
    this.numberOfGroups = numberOfGroups;
    return this;
  }

  public ActivityBuilder withName(String name) {
    this.name = name;
    return this;
  }
}
