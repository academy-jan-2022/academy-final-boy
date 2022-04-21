package codurance.academyfinalboy.backend.model.token;

import java.time.LocalDateTime;

public class TimeProvider {
    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }
}
