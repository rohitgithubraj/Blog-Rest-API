package com.therajverma.blog.rest.api.service.impl;

import com.therajverma.blog.rest.api.entity.Comment;
import com.therajverma.blog.rest.api.entity.Post;
import com.therajverma.blog.rest.api.exception.ResourceNotFoundException;
import com.therajverma.blog.rest.api.repository.CommentRepository;
import com.therajverma.blog.rest.api.repository.PostRepository;
import com.therajverma.blog.rest.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Long postId, Comment comment) {
        // retrieve post by id
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        // set post to comment
        comment.setPost(post);
        // save comment to database
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentByPostId(Long postId) {
        // retrieve comments by post id
        return commentRepository.findByPostId(postId);
    }

    @Override
    public Comment getCommentById(Long postId, Long commentId) {
        // retrieve post by id
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        // retrieve comment by comment id
        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
        // check if comment belongs to post
        if(!comment.getPost().getId().equals(post.getId())) {
            throw new RuntimeException("Comment does not belong to post");
        }
        return comment;
    }

    @Override
    public Comment updateComment(Long postId, Long commentId, Comment updateComment) {
        // retrieve post by id
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
        // retrieve comment by comment id
        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));
        // check if comment belongs to post
        if(!comment.getPost().getId().equals(post.getId())) {
            throw new RuntimeException("Comment does not belong to post");
        }
        comment.setName(updateComment.getName());
        comment.setEmail(updateComment.getEmail());
        comment.setBody(updateComment.getBody());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        // retrieve post by id
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
        // retrieve comment by comment id
        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));
        // check if comment belongs to post
        if(!comment.getPost().getId().equals(post.getId())) {
            throw new RuntimeException("Comment does not belong to post");
        }
        commentRepository.delete(comment);
    }
}