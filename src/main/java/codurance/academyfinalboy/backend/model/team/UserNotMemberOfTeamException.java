package codurance.academyfinalboy.backend.model.team;

public class UserNotMemberOfTeamException extends Exception {
    UserNotMemberOfTeamException(String message){
        super(message);
    }
}
