package codurance.academyfinalboy.backend.model.team;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Data
@Table("activity_group")
public class Group {
  @Id private Long id;

  @MappedCollection(idColumn = "group_id")
  private Set<ActivityMember> grouping;

  public Group(Set<ActivityMember> grouping) {
    this.grouping = grouping;
  }
}
