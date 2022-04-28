package codurance.academyfinalboy.backend.model.team;

public class UserNotMemberOfTeamException extends Exception {
    UserNotMemberOfTeamException(){
        super("Logged in user doesn't belong to this team");
    }
}
