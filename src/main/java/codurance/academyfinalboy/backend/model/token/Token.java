package codurance.academyfinalboy.backend.model.token;

import lombok.Data;

@Data
public class Token {
  private final long teamId;

  public Token(long teamId) {
    this.teamId = teamId;
  }
}
