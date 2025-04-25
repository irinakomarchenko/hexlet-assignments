package exercise.dto.users;

import exercise.model.User;
import lombok.Getter;
import lombok.AllArgsConstructor;
import java.util.List;

@Getter
@AllArgsConstructor
public class UsersPage {
    private List<User> users;
}
