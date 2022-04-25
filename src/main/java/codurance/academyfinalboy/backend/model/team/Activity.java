package codurance.academyfinalboy.backend.model.team;

import java.util.List;

public record Activity(String name, List<List<ActivityMember>> groups) {
}
