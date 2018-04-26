package javatraining.training.repositories;

import javatraining.training.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by Adela Vasilache on 24.04.2018
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Set<Tag> findByNameIn(Set<String> tagNames);

}
