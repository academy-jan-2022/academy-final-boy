package codurance.academyfinalboy.backend;

import codurance.academyfinalboy.backend.model.user.TeamRef;
import codurance.academyfinalboy.backend.model.user.User;

import java.util.Set;

public class UserBuilder {

  private long userId;
  private String fullName;
  private String externalId;
  private Set<TeamRef> teams;

  public UserBuilder() {
    userId = 1;
    fullName = "mario Johnson";
    externalId = "456456546-4456456";
    teams = Set.of(new TeamRef(1L));
  }

  public UserBuilder id(long userId) {
    this.userId = userId;
    return this;
  }

  public User build() {
    User user = new User(externalId, fullName);
    user.setId(userId);
    user.setTeams(teams);
    return user;
  }
}
