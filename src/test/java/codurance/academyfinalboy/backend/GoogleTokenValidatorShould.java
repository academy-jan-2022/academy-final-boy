package codurance.academyfinalboy.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.*;


public class GoogleTokenValidatorShould {
    String validToken = "valid_token";
    String invalidToken = "invalid_token";
    String oauthvalidateurl = "https://oauth2.googleapis.com/tokeninfo";


    @Test
    void return_true_when_token_is_valid() throws GeneralSecurityException, IOException {
        GoogleTokenValidator googleTokenValidator = new GoogleTokenValidator();
        assertTrue(googleTokenValidator.authenticateToken(validToken));

    }
    @Test
    void return_false_when_token_is_invalid() throws Exception {
        GoogleTokenValidator googleTokenValidator = new GoogleTokenValidator();
        assertFalse(googleTokenValidator.authenticateToken(invalidToken));

       /* GoogleTokenValidator googleTokenValidator = new GoogleTokenValidator();

        assertTrue(googleTokenValidator.authenticateToken(invalidToken));*/

        //when(googleTokenValidator.authenticateToken(invalidToken)).thenReturn(false);

        /*
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://oauth2.googleapis.com/tokeninfo?id_token="+invalidToken))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());*/



    }
}
