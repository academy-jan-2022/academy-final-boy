package codurance.academyfinalboy.backend;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service

public class GoogleTokenValidator {
    public Boolean authenticateToken(String token) throws GeneralSecurityException, IOException {
        return true;
        //        String CLIENT_ID = "";
//
//        JacksonFactory jsonFactory = new JacksonFactory();
//        HttpTransport transport = new NetHttpTransport();
//
//        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
//                .setAudience(Collections.singletonList(CLIENT_ID))
//                .build();
//
//        GoogleIdToken idToken = verifier.verify(token);
//        return idToken != null;

    }
}
