package codurance.academyfinalboy.backend.web.controllers;

import codurance.academyfinalboy.backend.actions.InvalidUserException;
import codurance.academyfinalboy.backend.model.team.UserNotMemberOfTeamException;
import codurance.academyfinalboy.backend.model.token.InvalidTokenException;
import com.microsoft.applicationinsights.TelemetryClient;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler {

  private final TelemetryClient telemetryClient;
  private final Map<Class<? extends Exception>, HttpStatus> exceptionToStatusMap;

  public RestExceptionHandler(TelemetryClient telemetryClient) {
    this.telemetryClient = telemetryClient;
    this.exceptionToStatusMap =
        Map.of(
            InvalidUserException.class,
            HttpStatus.UNAUTHORIZED,
            UserNotMemberOfTeamException.class,
            HttpStatus.FORBIDDEN,
            InvalidTokenException.class,
            HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<Object> handleIllegalState(Exception ex, WebRequest request) {
    telemetryClient.trackException(ex);

    HttpStatus status = exceptionToStatusMap.getOrDefault(ex.getClass(), HttpStatus.BAD_REQUEST);

    return ResponseEntity.status(status).body(new ErrorResponse(ex.getMessage()));
  }

  private record ErrorResponse(String message) {}
}
