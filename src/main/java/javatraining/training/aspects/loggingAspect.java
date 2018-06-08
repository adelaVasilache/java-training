package javatraining.training.aspects;

import javatraining.training.dtos.LogDto;
import javatraining.training.mappers.LogMapper;
import javatraining.training.services.domain.LogService;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Adela Vasilache on 24.05.2018
 */
@Component
@Aspect
public class loggingAspect {
    private final LogService logService;
    private final LogMapper logMapper;
    static Logger log = Logger.getLogger(loggingAspect.class.getName());

    @Autowired
    public loggingAspect(LogService logService) {
        this.logService = logService;
        this.logMapper = LogMapper.INSTANCE;
    }

    @AfterThrowing(value = "javatraining.training.aspects.PointcutDefinition.adviceLayer() && " +
    "args(e)")
    public void logException(JoinPoint jp, Exception e){
        LogDto logDto = LogDto.builder().created(new Date()).description(e.getMessage()).
                event(jp.getSignature().getName()).
                userId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()).build();
        logService.save(logMapper.toLog(logDto));
    }

    @AfterReturning(value = "javatraining.training.aspects.PointcutDefinition.businessLayer() && " +
    "args(object,..)")
    public void logGeneric(JoinPoint jp, Object object){
        LogDto logDto = LogDto.builder().created(new Date()).description(object.toString()).
                event(jp.getSignature().getName()).
                userId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()).build();

        logService.save(logMapper.toLog(logDto));

    }

}
