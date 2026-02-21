package com.therajverma.blog.rest.api.service.impl;

import com.therajverma.blog.rest.api.entity.Post;
import com.therajverma.blog.rest.api.exception.ResourceNotFoundException;
import com.therajverma.blog.rest.api.service.PostService;
import com.therajverma.blog.rest.api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }
    public Post getPostById(Long id) {
        Post post = postRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", id));
        return post;
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }
    public Post updatePost(Long id, Post postUpdate) {
        // fetch post by id
        Post post = postRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", id));
        // set the updated values
        post.setTitle(postUpdate.getTitle());
        post.setDescription(postUpdate.getDescription());
        post.setContent(postUpdate.getContent());
        // save the updated post
        return postRepository.save(post);
    }
    public void deletePost(Long id) {
        Post post = postRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", id));
        postRepository.delete(post);
    }

}
