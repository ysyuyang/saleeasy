package cn.supstore.core.base.locker.impl;

import java.lang.management.ManagementFactory;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Component;

import cn.supstore.core.base.locker.Locker;
import cn.supstore.core.base.locker.LockerManager;

/**
 * Redis实现，使用redis setnx功能实现乐观锁锁定过程 ，获得锁后，再设置过期时间
 * 
 * <p>Created by liusijin on 2017/5/23.
 */
@Component
public class RedisLockerManager implements LockerManager{
    private static final Logger log = LoggerFactory.getLogger(RedisLockerManager.class);

    @Resource(name="redisTemplate")
    private RedisOperations<String, String> ops;

    /* (non-Javadoc)
     * @see cn.supstore.core.base.locker.LockerManager#lockFor(java.lang.String)
     */
    @Override
    public Optional<Locker> lockFor(String keyName) {
        return this.lockFor(keyName,LockerManager.DEFAULT_EXPIRY_TIME_MILLIS);
    }

    /* (non-Javadoc)
     * @see cn.supstore.core.base.locker.LockerManager#lockFor(java.lang.String, long)
     */
    @Override
    public Optional<Locker> lockFor(String keyName, long expiryTime) {
        if(expiryTime<1)
            expiryTime = LockerManager.DEFAULT_EXPIRY_TIME_MILLIS;

        BoundValueOperations<String, String> obj = ops.boundValueOps(keyName);
        String specialValue = "_T"+Thread.currentThread().getName()+"_"+ ManagementFactory.getRuntimeMXBean().getName();
        Boolean succeed = obj.setIfAbsent(specialValue);
        if(succeed && obj.get().equals(specialValue)) {
            if(log.isDebugEnabled())
                log.debug("locker  [succeed] for: "+keyName);
            obj.expire(expiryTime,TimeUnit.MILLISECONDS);
            return Optional.of(new SimpleLocker(keyName, specialValue, obj));
        }
        else {
            if(log.isDebugEnabled())
                log.debug("locker  [failed] for: "+keyName);
            return Optional.empty();
        }
    }
}
