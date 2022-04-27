package codurance.academyfinalboy.backend.model.team;

import java.util.List;
import lombok.Data;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("activity_group")
public class Group {
  @MappedCollection(idColumn = "team_id", keyColumn = "group_key")
  private List<ActivityMember> grouping;

  public Group(List<ActivityMember> grouping) {
    this.grouping = grouping;
  }
}
