package service.serviceImpl;


import lombok.RequiredArgsConstructor;
import model.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import repository.PostRepository;
import service.PostService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Post createPost(String userId, String textStatus, List<MultipartFile> mediaFiles) {
        List<String> mediaUrls = new ArrayList<>();

        if (mediaFiles != null && !mediaFiles.isEmpty()) {
            if (mediaFiles.size() > 3) {
                throw new RuntimeException("Only up to 3 media files allowed.");
            }
            for (MultipartFile file : mediaFiles) {
                mediaUrls.add(file.getOriginalFilename()); // Placeholder for real URL
            }
        }

        Post post = Post.builder()
                .userId(userId)
                .textStatus(textStatus)
                .mediaUrls(mediaUrls)
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

