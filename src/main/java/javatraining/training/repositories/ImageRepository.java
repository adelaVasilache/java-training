package javatraining.training.repositories;

import javatraining.training.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by Adela Vasilache on 25.04.2018
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Set<Image> findByFileNameIn(Set<String> imageNames);
}
