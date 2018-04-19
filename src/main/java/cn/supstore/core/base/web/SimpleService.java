package cn.supstore.core.base.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liusijin on 2016/5/31.
 */
@Transactional
@Repository
@Scope("prototype")
public class SimpleService<T> extends GenericService<T,String> {


    public  SimpleService(){
        System.out.println("default construct");
    }

    public SimpleService(Class<T> entityClass){
        super.setEntityClass(entityClass);
    }


}
