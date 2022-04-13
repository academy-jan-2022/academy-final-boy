package codurance.academyfinalboy.backend.configurations;

import codurance.academyfinalboy.backend.infrastructure.services.GoogleTokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

  @Value("${should.filter.requests:true}")
  private boolean shouldFilterRequests;

  @Autowired AuthenticatedUser authenticatedUser;


  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    if (shouldFilterRequests) {
      registry.addInterceptor(new GoogleTokenValidator(authenticatedUser, new RestTemplate()));
    }
  }
}
