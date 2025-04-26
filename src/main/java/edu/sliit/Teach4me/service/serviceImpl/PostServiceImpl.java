package edu.sliit.Teach4me.service.serviceImpl;


import lombok.RequiredArgsConstructor;
import edu.sliit.Teach4me.model.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import edu.sliit.Teach4me.repository.PostRepository;
import edu.sliit.Teach4me.service.PostService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Post createPost(String userId, String textStatus, List<String> mediaFiles) {
        System.out.println(textStatus);

        Post post = Post.builder()
                .userId(userId)
                .textStatus(textStatus)
                .mediaUrls(mediaFiles)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return postRepository.save(post);
    }

    @Override
    public Post updatePost(String postId, String textStatus) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setTextStatus(textStatus);
        post.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Override
    public void deletePost(String postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getPostsByUserId(String userId) {
        return postRepository.findByUserId(userId);
    }
}

