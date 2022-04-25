package codurance.academyfinalboy.backend.model.team;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ActivityShould {

  @Test
  void throw_exception_on_team_smaller_than_3() {
    Assertions.assertThrows(
        IllegalStateException.class,
        () -> new Activity("name", List.of(new ActivityMember("1"), new ActivityMember("2")), 2));
  }
}
