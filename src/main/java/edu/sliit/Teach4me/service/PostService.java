package edu.sliit.Teach4me.service;

import edu.sliit.Teach4me.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    Post createPost(String userId, String textStatus, List<String> mediaFiles);
    Post updatePost(String postId, String textStatus);
    void deletePost(String postId);
    List<Post> getAllPosts();
    List<Post> getPostsByUserId(String userId);
}
