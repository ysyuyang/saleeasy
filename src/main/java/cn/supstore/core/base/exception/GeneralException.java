package cn.supstore.core.base.exception;

import cn.supstore.core.base.util.MsgUtil;

/**
 * Created by liusijin on 2016/5/23.
 */
public class GeneralException extends RuntimeException {

    private String msg;

    public String getMsg(){
        return this.msg;
    }

    public GeneralException(){
        super("exception.unknow");
    }

    public GeneralException(Throwable throwable) {
        super(throwable);
        this.msg = MsgUtil.getMessageByCode(throwable.getMessage());
    }

    public GeneralException (String code) {
        this.msg = MsgUtil.getMessageByCode(code);
    }


    public GeneralException (String code, Throwable throwable) {
        super(throwable);
        this.msg = MsgUtil.getMessageByCode(code);
    }
}
