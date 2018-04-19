package cn.supstore.core.base.schedule;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.temporal.ChronoUnit;

/**
 * 加入注解后，任务会使用全局锁竞争执行权
 * <p>Created by liusijin on 2017/5/23.
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SchedulerLock {
    String name() default "";
    int expiry() default -1;
    ChronoUnit expiryUnit() default ChronoUnit.SECONDS;
}
