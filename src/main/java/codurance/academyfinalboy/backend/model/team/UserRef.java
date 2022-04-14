package codurance.academyfinalboy.backend.model.team;

import lombok.Data;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("user_team")
public class UserRef {
  @Column("user_id")
  private final AggregateReference<Team, Long> userId;

  public UserRef(Long userId) {
    this.userId = AggregateReference.to(userId);
  }
}
