package codurance.academyfinalboy.backend.actions;

public class InvalidUserException extends Exception {
  public InvalidUserException() {
    super("User does not exist");
  }
}
