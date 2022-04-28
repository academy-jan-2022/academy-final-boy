package codurance.academyfinalboy.backend.model.team;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import org.junit.jupiter.api.Test;

class TeamShould {

  @Test
  void add_a_user_to_a_team() {
    long teamCreatorId = 1L;
    long newMemberId = 30L;
    Team team = new Team("Name", "Description", teamCreatorId);

    team.addMember(newMemberId);

    assertThat(team.getMembers(), contains(new UserRef(newMemberId), new UserRef(teamCreatorId)));
  }

  @Test
  void remove_a_user_to_a_team() {
    long teamCreatorId = 1L;
    long currentMemberId = 30L;
    Team team = new Team("Name", "Description", teamCreatorId);

    team.addMember(currentMemberId);
    assertThat(
        team.getMembers(), contains(new UserRef(currentMemberId), new UserRef(teamCreatorId)));

    team.removeMember(currentMemberId);
    assertThat(team.getMembers(), contains(new UserRef(teamCreatorId)));
  }
}
