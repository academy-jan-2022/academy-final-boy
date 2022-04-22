package codurance.academyfinalboy.backend.model.team;

import codurance.academyfinalboy.backend.model.user.User;
import java.util.List;

public class TeamView {

    String id;
    String name;
    String description;
    List<User> teamMembers;

    public TeamView(Team team, List<User> users) {
        this.id = team.getId().toString();
        this.name = team.getName();
        this.description = team.getDescription();

        this.teamMembers = users;
    }
}
