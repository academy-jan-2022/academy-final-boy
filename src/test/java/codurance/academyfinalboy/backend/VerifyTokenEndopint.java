package codurance.academyfinalboy.backend;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class TokenVerificationEndpointShould {

  @Test
  void unauthorized_calls_without_a_valid_token() throws IOException {
    //Given
    HttpUriRequest request = new HttpPost("http://localhost:8080/actuator/tokenvalidator");
    //When
    HttpResponse response = HttpClientBuilder.create().build().execute(request);
    //THEN
    Assertions.assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
  }
}
