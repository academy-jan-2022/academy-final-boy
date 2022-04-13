package codurance.academyfinalboy.backend;

import codurance.academyfinalboy.backend.configurations.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BackendApplication {
  public static void main(String[] args) {
    SpringApplication.run(BackendApplication.class, args);
  }

  @Value("${cors.url}")
  private String corsUrl;

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
            .allowedOrigins(corsUrl)
            .maxAge(3600);
      }
    };
  }

  @Bean
  public static RestTemplate getRestTemplate() {
    return new RestTemplate();
  }

  @Bean
  @Profile("test")
  public static AuthenticatedUser getAuthenticatedUser() {
    AuthenticatedUser authenticatedUser = new AuthenticatedUser();
    authenticatedUser.setExternalId("ba222e1f-85a2-463a-8e7d-53d3d8a16408");
    return authenticatedUser;
  }
}
