package codurance.academyfinalboy.backend;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;

@WebMvcTest(TokenVerificationController.class)
public class TokenVerificationShould {

  String endpointUrl = "http://localhost:8080/tokenvalidator";
  HttpClient client;

  @BeforeEach
  void setUp() {
    client = HttpClient.newHttpClient();
  }

  @Disabled
  @Test
  void authorized_calls_with_a_valid_token_return_a_200() throws Exception {
    String validToken = "[fill_with_a_valid_token]";

    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(new URI(endpointUrl))
            .setHeader("Authorization", validToken)
            .GET()
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    assertTrue(HttpStatus.valueOf(response.statusCode()).is2xxSuccessful());
  }

  @Test
  void unauthorized_calls_with_a_missing_header_authorization() throws Exception {
    HttpRequest request = HttpRequest.newBuilder().uri(new URI(endpointUrl)).GET().build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    assertTrue(HttpStatus.valueOf(response.statusCode()).is4xxClientError());
  }

  @Test
  void unauthorized_calls_with_a_empty_header_authorization() throws Exception {
    String validToken = "";
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(new URI(endpointUrl))
            .setHeader("Authorization", validToken)
            .GET()
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    assertTrue(HttpStatus.valueOf(response.statusCode()).is4xxClientError());
  }

  @Test
  void unauthorized_calls_with_a_invalid_header_authorization() throws Exception {
    String validToken = "Invalid_token";
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(new URI(endpointUrl))
            .setHeader("Authorization", validToken)
            .GET()
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    assertTrue(HttpStatus.valueOf(response.statusCode()).is4xxClientError());
  }
}
