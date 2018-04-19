package cn.supstore.core.base.locker;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 集群锁生成器
 * 
 * @TODO 对生成的锁进行状态管理 
 * 
 * <p>Created by liusijin on 2017/5/23.
 */
public interface LockerManager {

    public static final long DEFAULT_EXPIRY_TIME_MILLIS = TimeUnit.MINUTES.toMillis(30);

    /**
     * @param keyName 锁名称，集群同一时间唯一
     * @return 锁 
     * @see Locker
     */
    Optional<Locker> lockFor(String keyName);

    /**
     * @param keyName 锁名称，集群同一时间唯一
     * @param expiryTime 锁有效期长度
     * @return 锁
     */
    Optional<Locker> lockFor(String keyName,long expiryTime);

}
