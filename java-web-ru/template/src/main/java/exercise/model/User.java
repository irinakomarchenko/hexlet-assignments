package exercise.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;

@Getter
@Setter
@ToString
@AllArgsConstructor
public final class User {

    private long id;
    private String firstName;

    @ToString.Include
    private String lastName;

    private String email;
}