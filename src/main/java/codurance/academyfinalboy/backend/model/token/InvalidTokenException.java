package codurance.academyfinalboy.backend.model.token;

public class InvalidTokenException extends Exception{
    public InvalidTokenException(String errorMessage) {
        super(errorMessage);
    }
}
