package codurance.academyfinalboy.backend.configurations;

import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUser {
  private String externalId;

  public String getExternalId() {
    return externalId;
  }

  public void setExternalId(String externalId) {
    this.externalId = externalId;

  }
}
