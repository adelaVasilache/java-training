package javatraining.training.services.domain.impl;

import javatraining.training.models.Log;
import javatraining.training.repositories.LogRepository;
import javatraining.training.services.domain.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Adela Vasilache on 24.05.2018
 */
@Service
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void save(Log log) {
        logRepository.save(log);
    }
}
