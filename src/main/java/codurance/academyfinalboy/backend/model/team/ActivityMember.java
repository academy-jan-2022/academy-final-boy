package codurance.academyfinalboy.backend.model.team;

import lombok.Data;

@Data
public class ActivityMember {
  private String name;

  public ActivityMember(String name) {
    this.name = name;
  }
}
