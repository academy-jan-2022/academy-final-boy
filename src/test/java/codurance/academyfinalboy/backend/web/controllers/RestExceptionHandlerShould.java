package codurance.academyfinalboy.backend.web.controllers;

import codurance.academyfinalboy.backend.BaseSpringTest;
import codurance.academyfinalboy.backend.actions.InvalidUserException;
import com.microsoft.applicationinsights.TelemetryClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class RestExceptionHandlerShould extends BaseSpringTest {

    @MockBean TelemetryClient telemetryClient;
    @Mock WebRequest request;

    @Test void
    return_UNAUTHORIZED_status_on_invalid_user_exception(){
        HttpStatus expectedResponseStatus = HttpStatus.UNAUTHORIZED;

        RestExceptionHandler restExceptionHandler = new RestExceptionHandler(telemetryClient);

        InvalidUserException exception = new InvalidUserException();

        var response = restExceptionHandler.handleIllegalState(exception, request);

        assertThat(response.getStatusCode(), equalTo(expectedResponseStatus));
    }
}
