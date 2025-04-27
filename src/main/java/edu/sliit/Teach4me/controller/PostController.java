package edu.sliit.Teach4me.controller;

import lombok.RequiredArgsConstructor;
import edu.sliit.Teach4me.model.Post;
import edu.sliit.Teach4me.dto.CreatePostRequest;
import edu.sliit.Teach4me.dto.UpdatePostRequest;
import edu.sliit.Teach4me.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
//
    @PostMapping()
    public ResponseEntity<Post> createPost(
            @RequestBody CreatePostRequest request
    ) {
        return ResponseEntity.ok(postService.createPost(request.getUserId(), request.getTextStatus(), request.getMediaFiles()));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(
            @PathVariable String postId,
            @RequestBody UpdatePostRequest request
    ) {
        return ResponseEntity.ok(postService.updatePost(postId, request.getTextStatus()));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable String postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable String userId) {
        return ResponseEntity.ok(postService.getPostsByUserId(userId));
    }
}
