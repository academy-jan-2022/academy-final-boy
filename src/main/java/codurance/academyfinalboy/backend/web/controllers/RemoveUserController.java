package codurance.academyfinalboy.backend.web.controllers;

import codurance.academyfinalboy.backend.actions.RemoveUser;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemoveUserController {
    private final RemoveUser removeUser;

    public RemoveUserController(RemoveUser removeUser) {
        this.removeUser = removeUser;
    }

    @DeleteMapping("/remove-user")
    void removeUser(@RequestParam Long teamId) {
        removeUser.execute(teamId);
    }
}
