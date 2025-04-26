package edu.sliit.Teach4me.repository;

import edu.sliit.Teach4me.model.Post;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByUserId(String userId);
}



