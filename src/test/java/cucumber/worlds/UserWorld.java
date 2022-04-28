package cucumber.worlds;

import codurance.academyfinalboy.backend.model.user.User;
import java.util.ArrayList;
import java.util.List;

public class UserWorld {
  public static List<User> storedUsers = new ArrayList<>();
  public static User currentUser;
}
