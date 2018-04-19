package cn.supstore.core.base.locker;

/**
 * 集群锁定义
 * 
 * Created by liusijin on 2017/5/23.
 */
public interface Locker {

    /**
     * 释放锁
     * @return
     */
    boolean release();
    
    /**
     * 当前锁是否已经过期
     * @return
     */
    boolean isAvailable();
    
    /**
     * 锁名称
     * @return
     */
    String getKeyName();
    
    /**
     * 锁定值
     * @return
     */
    String getLockerValue();
}
