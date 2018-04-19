package cn.supstore.core.base.util;

import cn.supstore.core.base.AppUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * Created by liusijin on 2016/5/23.
 */
public class MsgUtil {

    public static final String EXCEPTION_409 = getMessageByCode("exception.409");

    public static String getMessageByCode(String code){
        MessageSource source = null;
        try {
            source = AppUtil.getCtx().getBean(MessageSource.class);
        } catch (Exception e) {
            return code;
        }
        String msg =  source.getMessage(code, null ,Locale.ROOT);
        if(msg == null||msg.equals(""))
            msg = code;
        return msg;
    }


}
