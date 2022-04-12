package codurance.academyfinalboy.backend.infrastructure.services;

import codurance.academyfinalboy.backend.configurations.AuthenticatedUser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class GoogleTokenValidator implements HandlerInterceptor {

  private final AuthenticatedUser authenticatedUser;

  public GoogleTokenValidator(AuthenticatedUser authenticatedUser) {
    this.authenticatedUser = authenticatedUser;
  }

  record GoogleResponse(String sub) {}

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

  protected boolean authenticateToken(String token) {
    RestTemplate restTemplate = new RestTemplate();

    try {
      GoogleResponse responseObject =
          restTemplate.getForObject(
              "https://oauth2.googleapis.com/tokeninfo?id_token=" + token, GoogleResponse.class);

      authenticatedUser.setExternalId(responseObject.sub);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
