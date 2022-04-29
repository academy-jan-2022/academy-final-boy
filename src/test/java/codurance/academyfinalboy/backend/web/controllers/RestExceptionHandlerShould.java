package codurance.academyfinalboy.backend.web.controllers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import codurance.academyfinalboy.backend.BaseSpringTest;
import codurance.academyfinalboy.backend.actions.InvalidUserException;
import codurance.academyfinalboy.backend.model.team.UserNotMemberOfTeamException;
import codurance.academyfinalboy.backend.model.token.InvalidTokenException;
import com.microsoft.applicationinsights.TelemetryClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

public class RestExceptionHandlerShould extends BaseSpringTest {

  @MockBean TelemetryClient telemetryClient;
  @Mock WebRequest request;
  private RestExceptionHandler restExceptionHandler;

  @BeforeEach
  void setUp() {
    restExceptionHandler = new RestExceptionHandler(telemetryClient);
  }

  @Test
  void return_UNAUTHORIZED_status_on_invalid_user_exception() {
    HttpStatus expectedResponseStatus = HttpStatus.UNAUTHORIZED;
    InvalidUserException exception = new InvalidUserException();

    var response = restExceptionHandler.handleIllegalState(exception, request);

    assertThat(response.getStatusCode(), equalTo(expectedResponseStatus));
  }

  @Test
  void return_FORBIDDEN_status_on_user_not_member_of_team_exception() {
    HttpStatus expectedResponseStatus = HttpStatus.FORBIDDEN;
    UserNotMemberOfTeamException exception = new UserNotMemberOfTeamException();

    var response = restExceptionHandler.handleIllegalState(exception, request);

    assertThat(response.getStatusCode(), equalTo(expectedResponseStatus));
  }

  @Test
  void return_NOT_FOUND_status_on_invalid_token_exception() {
    HttpStatus expectedResponseStatus = HttpStatus.NOT_FOUND;
    InvalidTokenException exception = new InvalidTokenException("Invalid token");

    var response = restExceptionHandler.handleIllegalState(exception, request);

    assertThat(response.getStatusCode(), equalTo(expectedResponseStatus));
  }

  @Test
  void return_BAD_REQUEST_status_on_non_defined_exceptions() {
    HttpStatus expectedResponseStatus = HttpStatus.BAD_REQUEST;
    Exception exception = new Exception();

    var response = restExceptionHandler.handleIllegalState(exception, request);

    assertThat(response.getStatusCode(), equalTo(expectedResponseStatus));
  }
}
