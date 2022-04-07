package codurance.academyfinalboy.backend;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleTokenValidatorShould {

    @Test
    void return_true_when_token_is_valid() {
        String validToken = "super_secure_valid_token";
        GoogleTokenValidator googleTokenValidator = new GoogleTokenValidator();
        assertTrue(googleTokenValidator.verify(validToken));

    }
}
