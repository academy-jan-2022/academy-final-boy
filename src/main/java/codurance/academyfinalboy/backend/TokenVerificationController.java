package codurance.academyfinalboy.backend;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TokenVerificationController {

    @PostMapping("/tokenvalidator")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void getTodoLists() {
    }
}
