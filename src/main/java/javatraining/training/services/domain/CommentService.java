package javatraining.training.services.domain;

import javatraining.training.models.Comment;
import javatraining.training.models.Post;

import java.util.List;

/**
 * Created by Adela Vasilache on 13.06.2018
 */
public interface CommentService {
    List<Comment> getCommentsByPost(List<Post> posts);
}
