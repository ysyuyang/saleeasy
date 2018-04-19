package cn.supstore.core.base.exception;

/**
 * Created by liusijin on 2016/5/23.
 */
public class UnkownException extends GeneralException {
    public UnkownException(String code) {
        super(code);
    }

    public UnkownException(Throwable e) {
        super(e);
    }
}
