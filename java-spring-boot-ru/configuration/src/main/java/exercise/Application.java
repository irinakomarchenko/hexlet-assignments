package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import  org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Getter;
import lombok.Setter;

import exercise.model.User;
import exercise.component.UserProperties;

@SpringBootApplication
@RestController
public class Application {

    private List<User> users = Data.getUsers();

    // BEGIN
    @Component
    @ConfigurationProperties(prefix = "users")
    @Getter @Setter
    public static class UserProperties {
        private List<String> admins;
    }

    @Autowired
    private UserProperties userProperties;

    @GetMapping("/admins")
    public List<String> getAdmins() {
        List<String> adminEmails = userProperties.getAdmins();
        return users.stream()
                .filter(user -> adminEmails.contains(user.getEmail()))
                .map(User::getName)
                .sorted(String::compareToIgnoreCase)
                .toList();
    }
    // END

    @GetMapping("/users")
    public List<User> index() {
        return users;
    }

    @GetMapping("/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        var user = users.stream()
            .filter(u -> u.getId() == id)
            .findFirst();
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
