package codurance.academyfinalboy.backend.web.controllers;

import com.microsoft.applicationinsights.TelemetryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler {

  private final TelemetryClient telemetryClient;

  public RestExceptionHandler(TelemetryClient telemetryClient) {
    this.telemetryClient = telemetryClient;
  }

  @ExceptionHandler(value = {Exception.class})
  protected ResponseEntity<Object> handleIllegalState(RuntimeException ex, WebRequest request) {
    telemetryClient.trackException(ex);

    return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
  }

  private record ErrorResponse(String message) {}
}
