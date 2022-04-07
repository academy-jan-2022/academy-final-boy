package codurance.academyfinalboy.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;

@RestController
public class TokenVerificationController {

    @Autowired
    private GoogleTokenValidator googleTokenValidator;

    @GetMapping ("/tokenvalidator")

    public ResponseEntity<?> isTokenValid(@RequestHeader("Authorization") String token) throws GeneralSecurityException, IOException, InterruptedException, URISyntaxException {
        Boolean isTokenValid = googleTokenValidator.authenticateToken(token);

        if(isTokenValid){
            return ResponseEntity.ok().build();
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
