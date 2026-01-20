package io.river.spring_lock.standard.dto.retrylock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

import jakarta.persistence.OptimisticLockException;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Order(Ordered.LOWEST_PRECEDENCE - 1)    // @Transactional보다 더 먼저 메서드에 달라 붙기 위해, 더 높은 우선순위 부여
@Component
@Slf4j
public class OptimisticLockRetryAspect {

	@Around("@annotation(retryOnOptimisticLock)")
	public Object around(ProceedingJoinPoint joinPoint, RetryOnOptimisticLock retryOnOptimisticLock) throws Throwable {
		int maxAttempts = retryOnOptimisticLock.attempts();
		long backoff = retryOnOptimisticLock.backoff();

		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			try {
				Object o = joinPoint.proceed();    // 메소드 실행

				return o;
			} catch (ObjectOptimisticLockingFailureException | OptimisticLockException e) {
				if (attempt < maxAttempts) {
					Thread.sleep(backoff);
				} else {
					throw e;
				}
			} catch (Exception e) {
				throw e;
			}
		}

		throw new IllegalStateException("Unexpected state: OptimisticLockingRetryFailed in AOP");
	}
}
