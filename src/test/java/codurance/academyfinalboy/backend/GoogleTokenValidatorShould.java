package codurance.academyfinalboy.backend;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleTokenValidatorShould {

    @Test
    void return_true_when_token_is_valid() throws GeneralSecurityException, IOException {
        String validToken = "";
        GoogleTokenValidator googleTokenValidator = new GoogleTokenValidator();
        assertTrue(googleTokenValidator.authenticateToken(validToken));

    }
}
