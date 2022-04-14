package codurance.academyfinalboy.backend.actions;

import codurance.academyfinalboy.backend.model.user.UserService;

public class GetTeams {
    private  UserService userService;

    public GetTeams(UserService userService) {
        this.userService = userService;
    }

    public String execute() {
      throw new UnsupportedOperationException();
    }
}
