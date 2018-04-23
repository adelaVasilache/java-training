package javatraining.training.repositories;

import javatraining.training.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Adela Vasilache on 23.04.2018
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
