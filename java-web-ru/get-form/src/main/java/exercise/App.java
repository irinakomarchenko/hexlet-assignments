package exercise;

import io.javalin.Javalin;
import java.util.List;
import exercise.model.User;
import exercise.dto.users.UsersPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;
import java.util.Collections;
import java.util.Optional;


import org.apache.commons.lang3.StringUtils;

public final class App {

    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get("/users", ctx -> {
            var term = Optional.ofNullable(ctx.queryParam("term")).orElse("");

            var users = USERS.stream()
                    .filter(u -> StringUtils.startsWithIgnoreCase(u.getFirstName(), term))
                    .toList();

            var page = new UsersPage(users, term);

            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
