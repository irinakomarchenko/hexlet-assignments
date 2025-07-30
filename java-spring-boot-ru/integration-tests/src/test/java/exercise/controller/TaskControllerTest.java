package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    @Test
    public void testShow() throws Exception {
        var task = new Task();
        task.setTitle(faker.lorem().sentence(2));
        task.setDescription(faker.lorem().sentence(4));
        taskRepository.save(task);

        var result = mockMvc.perform(get("/tasks/{id}", task.getId()))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body)
                .and(
                        a -> a.node("title").isEqualTo(task.getTitle()),
                        a -> a.node("description").isEqualTo(task.getDescription())
                );
    }

    @Test
    public void testCreate() throws Exception {
        var params = new HashMap<String, String>();
        params.put("title", faker.lorem().sentence(2));
        params.put("description", faker.lorem().sentence(4));

        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(params));

        var result = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body)
                .and(
                        a -> a.node("title").isEqualTo(params.get("title")),
                        a -> a.node("description").isEqualTo(params.get("description"))
                );

        assertThat(taskRepository.findAll())
                .anyMatch(t -> t.getTitle().equals(params.get("title")));
    }

    @Test
    public void testUpdate() throws Exception {
        var task = new Task();
        task.setTitle(faker.lorem().sentence(2));
        task.setDescription(faker.lorem().sentence(4));
        taskRepository.save(task);

        var updated = new HashMap<String, String>();
        updated.put("title", faker.lorem().sentence(3));
        updated.put("description", faker.lorem().sentence(3));

        var request = put("/tasks/{id}", task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(updated));

        var result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body)
                .and(
                        a -> a.node("title").isEqualTo(updated.get("title")),
                        a -> a.node("description").isEqualTo(updated.get("description"))
                );

        var actual = taskRepository.findById(task.getId()).get();
        assertThat(actual.getTitle()).isEqualTo(updated.get("title"));
    }

    @Test
    public void testDelete() throws Exception {
        var task = new Task();
        task.setTitle(faker.lorem().sentence(2));
        task.setDescription(faker.lorem().sentence(4));
        taskRepository.save(task);

        mockMvc.perform(delete("/tasks/{id}", task.getId()))
                .andExpect(status().isOk());

        assertThat(taskRepository.findById(task.getId())).isEmpty();
    }
    // END
}
