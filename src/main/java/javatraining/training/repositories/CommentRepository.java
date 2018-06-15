package javatraining.training.repositories;

import javatraining.training.models.Comment;
import javatraining.training.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Adela Vasilache on 13.06.2018
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostIn(List<Post> posts);
}
