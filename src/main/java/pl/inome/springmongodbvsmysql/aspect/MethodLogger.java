package pl.inome.springmongodbvsmysql.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodLogger {

  private static final Logger LOG = LoggerFactory.getLogger(MethodLogger.class);

  long startTime;
  long elapsedTime;

  @Around("execution(* *(..))  && @annotation(LogAspect)")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    startTime = System.nanoTime();
    Object result = joinPoint.proceed();
    elapsedTime = System.nanoTime() - startTime;
    LOG.info(" *** {}\t\texecution time:\t{} ms ***", joinPoint.getSignature().getName(),
            elapsedTime / 1000000);
    return result;
  }
}
