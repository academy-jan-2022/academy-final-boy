package codurance.academyfinalboy.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenVerificationController {

    @Autowired
    private GoogleTokenValidator googleTokenValidator;

    @PostMapping("/tokenvalidator")

    public ResponseEntity<?> isTokenValid(@RequestBody String token) {
        Boolean isTokenValid = googleTokenValidator.verify(token);

        if(isTokenValid){
            return ResponseEntity.ok().build();
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
