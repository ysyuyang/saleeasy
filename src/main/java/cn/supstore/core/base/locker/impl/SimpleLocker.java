package cn.supstore.core.base.locker.impl;

import org.springframework.data.redis.core.BoundValueOperations;

import cn.supstore.core.base.exception.DataException;
import cn.supstore.core.base.locker.Locker;

/**
 * redis 实现
 * <p>Created by liusijin on 2017/5/23.
 */
public class SimpleLocker implements Locker{
    private final String keyName;
    private final String localValue;
    private final BoundValueOperations<String, String> ops;

    public SimpleLocker(String keyName, String value, BoundValueOperations<String, String> obj) {
        this.keyName = keyName;
        this.localValue = value;
        this.ops = obj;
    }


    /* (non-Javadoc)
     * @see cn.supstore.core.base.locker.Locker#release()
     */
    @Override
    public boolean release() {
//        String serverValue = ops.get();
        if(isAvailable()){
            ops.getOperations().delete(this.keyName );
            return true;
        }else{
            throw new DataException("redis中没有该KEY:"+this.keyName);
        }

    }

    /* (non-Javadoc)
     * @see cn.supstore.core.base.locker.Locker#isAvailable()
     */
    @Override
    public boolean isAvailable() {
        String serverValue = ops.get();
        return serverValue!=null && localValue.equals(serverValue);
    }

    /* (non-Javadoc)
     * @see cn.supstore.core.base.locker.Locker#getKeyName()
     */
    @Override
    public String getKeyName() {
        return this.keyName;
    }

    /* (non-Javadoc)
     * @see cn.supstore.core.base.locker.Locker#getLockerValue()
     */
    @Override
    public String getLockerValue() {
        return this.getLockerValue();
    }
}
