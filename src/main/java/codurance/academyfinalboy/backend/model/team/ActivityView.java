package codurance.academyfinalboy.backend.model.team;

import java.util.ArrayList;
import java.util.List;

public class ActivityView {

    private String name;
    private List<List<Member>> groups;

    public ActivityView(Activity activity) {
        this.name = activity.getName();
        this.groups = new ArrayList<>();

        var activityGroups = activity.getGroups();
        activityGroups.forEach(group -> {

        });
    }

    private record Member(String name){}
}
