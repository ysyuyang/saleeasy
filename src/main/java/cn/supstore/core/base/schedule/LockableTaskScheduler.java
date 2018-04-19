package cn.supstore.core.base.schedule;

import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cn.supstore.core.base.exception.ServiceException;
import cn.supstore.core.base.locker.Locker;
import cn.supstore.core.base.locker.LockerManager;

/**
 * 实现spring {@link TaskScheduler}，Spring启动会使用该Bean配置计划任务
 * <p>加入{@link SchedulerLock}注解后，任务会使用全局锁竞争执行权
 * 
 * 
 * <p>Created by liusijin on 2017/5/23.
 */
@Component
public class LockableTaskScheduler  extends ConcurrentTaskScheduler implements TaskScheduler{
    private static final Logger log = LoggerFactory.getLogger(LockableTaskScheduler.class);
    
    private static final Map<String, Runnable> taskTraceMap = new HashMap<String,Runnable>();

    @Autowired LockerManager mng;

    /**
     * 配置线程池管理器
     */
    public LockableTaskScheduler() {
        super();
//        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);
        setScheduledExecutor(executor);
        setConcurrentExecutor(executor);
    }



    /**
     * 扫描{@link SchedulerLock}注解，配置集群锁，竞争获取锁后执行任务。
     * 如果没有注解则随机生成锁，直接执行
     * 
     * @param task 计划任务
     * @return 本机抢占到的锁，可能为空，使用optional 中 ifPresent(consumer)判定
     * @see SchedulerLock
     */
    public Optional<Locker> lockerFor(Runnable task) {
        if (task instanceof ScheduledMethodRunnable) {
            Method method = ((ScheduledMethodRunnable) task).getMethod();
            SchedulerLock annotation = AnnotationUtils.findAnnotation(method, SchedulerLock.class);
            String key = nameForTask(task);
            
            if (annotation!=null) {
            		long millisLater = Duration.of(annotation.expiry(), annotation.expiryUnit()).toMillis();
                return mng.lockFor(key, millisLater);
            }else{
                return mng.lockFor(key);
            }
        }
        return Optional.empty();
    }
    
    private String nameForTask(Runnable task) {
    		if (task instanceof ScheduledMethodRunnable) {
    			Method method = ((ScheduledMethodRunnable) task).getMethod();
            SchedulerLock annotation = AnnotationUtils.findAnnotation(method, SchedulerLock.class);
            
            String key;
            if (annotation!=null ) {
            		
                if(StringUtils.isEmpty(annotation.name()))
                    key = "scheduler_random_"+method.getName();
                else
                		key = "scheduler_"+annotation.name();
            }else{
                key = "scheduler_"+method.getName()+"_T"+Thread.currentThread().getName()+"_"+ ManagementFactory.getRuntimeMXBean().getName();
            }
            return key;
    		}
    		throw new ServiceException("名称获取失败,任务没有实现ScheduledMethodRunnable接口:"+task.getClass().getName());
    }
    
    

	private Runnable wrap(Runnable task) {
		trace(task);
		Runnable wraped = () -> {
			lockerFor(task).ifPresent(locker -> {
				try {
					if (locker.isAvailable()) {
						if (log.isDebugEnabled()) {
							log.debug("执行计划任务>>>>>>>> : " + locker.getKeyName());
							task.run();
							log.debug("结束计划任务<<<<<<<< : " + locker.getKeyName());
						} else {
							task.run();
						}
					}
				} finally {
					locker.release();
				}
			});
		};
		return wraped;
	}


    private void trace(Runnable task) {
    		taskTraceMap.putIfAbsent(nameForTask(task), task);
	}



	public static Map<String, Runnable> getTasktracemap() {
		return taskTraceMap;
	}



	/* (non-Javadoc)
     * @see org.springframework.scheduling.concurrent.ConcurrentTaskScheduler#schedule(java.lang.Runnable, org.springframework.scheduling.Trigger)
     */
    public ScheduledFuture<?> schedule(Runnable task, Trigger trigger) {
        return super.schedule(wrap(task),trigger);
    }

    /* (non-Javadoc)
     * @see org.springframework.scheduling.concurrent.ConcurrentTaskScheduler#schedule(java.lang.Runnable, java.util.Date)
     */
    public ScheduledFuture<?> schedule(Runnable task, Date startTime) {
        return super.schedule(wrap(task),startTime);
    }

    /* (non-Javadoc)
     * @see org.springframework.scheduling.concurrent.ConcurrentTaskScheduler#scheduleAtFixedRate(java.lang.Runnable, java.util.Date, long)
     */
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, Date startTime, long period) {
        return super.scheduleAtFixedRate(wrap(task),startTime,period);
    }

    /* (non-Javadoc)
     * @see org.springframework.scheduling.concurrent.ConcurrentTaskScheduler#scheduleAtFixedRate(java.lang.Runnable, long)
     */
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long period) {
        return super.scheduleAtFixedRate(wrap(task),  period) ;
    }

    /* (non-Javadoc)
     * @see org.springframework.scheduling.concurrent.ConcurrentTaskScheduler#scheduleWithFixedDelay(java.lang.Runnable, java.util.Date, long)
     */
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, Date startTime, long delay) {
        return super.scheduleWithFixedDelay(wrap(task),startTime,delay);
    }

    /* (non-Javadoc)
     * @see org.springframework.scheduling.concurrent.ConcurrentTaskScheduler#scheduleWithFixedDelay(java.lang.Runnable, long)
     */
    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, long delay) {
        return super.scheduleWithFixedDelay(wrap(task),delay);
    }
}
