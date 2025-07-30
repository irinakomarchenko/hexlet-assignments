package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private PostRepository repository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("")
    public List<PostDTO> index() {
        var posts = repository.findAll();
        return posts.stream().map(post -> {
            PostDTO dto = new PostDTO();
            dto.setId(post.getId());
            dto.setTitle(post.getTitle());
            dto.setBody(post.getBody());
            List<CommentDTO> comments = commentRepository.findByPostId(post.getId())
                    .stream()
                    .map(comment -> {
                        CommentDTO commentDTO = new CommentDTO();
                        commentDTO.setId(comment.getId());
                        commentDTO.setBody(comment.getBody());
                        return commentDTO;
                    })
                    .toList();
            dto.setComments(comments);
            return dto;
        }).toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO show(@PathVariable Long id) {
        var post = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        var dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());

        List<CommentDTO> comments = commentRepository.findByPostId(id)
                .stream()
                .map(comment -> {
                    CommentDTO commentDTO = new CommentDTO();
                    commentDTO.setId(comment.getId());
                    commentDTO.setBody(comment.getBody());
                    return commentDTO;
                })
                .toList();

        dto.setComments(comments);

        return dto;
    }
}
// END
