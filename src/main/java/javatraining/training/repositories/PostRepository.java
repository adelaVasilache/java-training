package javatraining.training.repositories;

import javatraining.training.models.Post;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Adela Vasilache on 23.04.2018
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findById(Long Id);

}
