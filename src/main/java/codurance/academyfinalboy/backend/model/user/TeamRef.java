package codurance.academyfinalboy.backend.model.user;

import codurance.academyfinalboy.backend.model.team.Team;
import lombok.Data;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("user_team")
public class TeamRef {
  @Column("team_id")
  private final AggregateReference<Team, Long> teamId;

  public TeamRef(Long teamId) {
    this.teamId = AggregateReference.to(teamId);
  }
}
