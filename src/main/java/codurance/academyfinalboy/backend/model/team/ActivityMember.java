package codurance.academyfinalboy.backend.model.team;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ActivityMember {
  @Id private Long id;
  private String name;

  public ActivityMember(String name) {
    this.name = name;
  }
}
