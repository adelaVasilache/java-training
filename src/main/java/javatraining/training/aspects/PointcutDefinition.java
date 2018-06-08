package javatraining.training.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by Adela Vasilache on 24.05.2018
 */
@Aspect
public class PointcutDefinition {
    @Pointcut("within(javatraining.training.controllers..*)")
    public void businessLayer(){}

    @Pointcut("within(javatraining.training.controllers.advice..*)")
    public void adviceLayer(){}
}
