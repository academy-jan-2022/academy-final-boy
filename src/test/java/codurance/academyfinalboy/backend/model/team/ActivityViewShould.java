package codurance.academyfinalboy.backend.model.team;

import static org.assertj.core.api.Assertions.assertThat;

import codurance.academyfinalboy.backend.builders.ActivityBuilder;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.junit.jupiter.api.Test;

class ActivityViewShould {

  @Test
  void convert_nested_group_classes_to_nested_list() {
    Activity activity = new ActivityBuilder().build();

    ActivityView activityView = new ActivityView(activity);

    Queue<Group> queue = new LinkedList<>(activity.getGroups());

    var group1 = queue.poll();
    var group2 = queue.poll();

    List<String> group1Members =
        group1.getGrouping().stream().map(ActivityMember::getName).toList();
    List<String> group2Members =
        group2.getGrouping().stream().map(ActivityMember::getName).toList();

    var actualGroup1Members =
        activityView.getGroups().get(0).stream().map(ActivityView.Member::name).toList();

    var actualGroup2Members =
        activityView.getGroups().get(1).stream().map(ActivityView.Member::name).toList();

    assertThat(actualGroup1Members).isEqualTo(group1Members);
    assertThat(actualGroup2Members).isEqualTo(group2Members);
  }
}
