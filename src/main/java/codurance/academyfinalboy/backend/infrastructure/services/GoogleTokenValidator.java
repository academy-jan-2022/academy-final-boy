package codurance.academyfinalboy.backend.infrastructure.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

@Service
public class GoogleTokenValidator implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if (request.getMethod().equals("OPTIONS")) {
      return true;
    }

    String authHeader = request.getHeader("Authorization");
    if (authHeader != null && authenticateToken(authHeader)) {
      return true;
    }
    response.sendError(401, "Unauthorized");
    return false;
  }

  protected Boolean authenticateToken(String token)
      throws IOException, InterruptedException, URISyntaxException {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(new URI("https://oauth2.googleapis.com/tokeninfo?id_token=" + token))
            .GET()
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    return HttpStatus.valueOf(response.statusCode()).is2xxSuccessful();
  }
}
