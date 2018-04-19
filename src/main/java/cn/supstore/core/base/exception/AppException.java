package cn.supstore.core.base.exception;

/**
 * Created by liusijin on 2016/5/23.
 */
public class AppException extends GeneralException{



    public static final void throwNew(String code){
        GeneralException exp = new GeneralException(code);
        throw exp;
    }

    public static final void throwNew(Exception e){
        GeneralException exp = new GeneralException(e);
        throw exp;
    }

    public static final void throwService(String code){
        ServiceException exp = new ServiceException(code);
        throw exp;
    }

    public static final void throwDataAccess(String code){
        DataException exp = new DataException(code);
        throw exp;
    }
}
