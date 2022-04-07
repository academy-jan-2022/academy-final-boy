package codurance.academyfinalboy.backend.user;

import java.util.Objects;
import java.util.UUID;

public class User {
  private final UUID externalId;
  private final String fullName;
  private final String username;
  private Long id;

  public User(UUID externalId, String fullName, String username) {
    this.externalId = externalId;
    this.fullName = fullName;
    this.username = username;
  }

  public UUID getExternalId() {
    return externalId;
  }

  public String getUsername() {
    return username;
  }

  public Long getId() {
    return id;
  }

  public String getFullName() {
    return fullName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(externalId, user.externalId)
        && Objects.equals(fullName, user.fullName)
        && Objects.equals(username, user.username)
        && Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(externalId, fullName, username, id);
  }
}
