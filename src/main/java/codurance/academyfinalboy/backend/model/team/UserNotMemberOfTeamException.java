package codurance.academyfinalboy.backend.model.team;

public class UserNotMemberOfTeamException extends Exception {
    public UserNotMemberOfTeamException(){
        super("Logged in user doesn't belong to this team");
    }
}
