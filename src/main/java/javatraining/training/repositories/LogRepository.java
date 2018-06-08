package javatraining.training.repositories;

import javatraining.training.models.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Adela Vasilache on 24.05.2018
 */
@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}
