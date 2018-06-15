package javatraining.training.services.domain.impl;

import javatraining.training.models.Comment;
import javatraining.training.models.Post;
import javatraining.training.repositories.CommentRepository;
import javatraining.training.services.domain.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Adela Vasilache on 13.06.2018
 */
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getCommentsByPost(List<Post> posts) {
        return commentRepository.findByPostIn(posts);
    }
}
