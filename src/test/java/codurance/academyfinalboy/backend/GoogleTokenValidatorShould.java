package codurance.academyfinalboy.backend;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class GoogleTokenValidatorShould {
    String validToken = "";
    String invalidToken = "invalid_token";

    @Test
    void return_true_when_token_is_valid() throws IOException, InterruptedException, URISyntaxException {
        GoogleTokenValidator googleTokenValidator = new GoogleTokenValidator();
        assertTrue(googleTokenValidator.authenticateToken(validToken));
    }
    @Test
    void return_false_when_token_is_invalid() throws Exception {
        GoogleTokenValidator googleTokenValidator = new GoogleTokenValidator();
        assertFalse(googleTokenValidator.authenticateToken(invalidToken));
    }

}
