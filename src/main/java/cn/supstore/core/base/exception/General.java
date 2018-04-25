package cn.supstore.core.base.exception;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.supstore.biz.model.TmEvent;
import cn.supstore.core.base.exception.AppException;
import cn.supstore.core.base.exception.GeneralException;
import cn.supstore.core.base.exception.UnkownException;
import cn.supstore.core.base.schedule.LockableTaskScheduler;
import cn.supstore.core.base.web.IService;

/**
 * Created by liusijin on 2016/5/17.
 */
@Controller
public class General {

    @Autowired
    IService<TmEvent,String> srv;
    
    @Autowired RedisTemplate<String, Object> redisTemplate;
    @Autowired LockableTaskScheduler scheduler;

    @RequestMapping(value = "/scheduler",method = RequestMethod.GET)
    public String scheduler(Model model, String key){
    		Map<String,Object> result = LockableTaskScheduler.getTasktracemap().entrySet().stream()
//        		.collect(Collectors.toMap(p -> p.getKey(), p -> ""+redisTemplate.boundValueOps(p.getKey()).get())
    				.collect(HashMap::new, (m,e)->m.put(e.getKey(), redisTemplate.boundValueOps(e.getKey()).get()), HashMap::putAll);

        model.addAttribute(result);
        return "hello_jsp";
    }
    
    @RequestMapping(value = "/scheduler/{key}/run",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity schedulerRun(Model model, @PathVariable String key){
    		if(key!=null) {
			Runnable runnable = LockableTaskScheduler.getTasktracemap().get(key);
			if(runnable!=null && redisTemplate.boundValueOps(key).get()==null) {
				scheduler.lockerFor(runnable).ifPresent(locker -> {
						if(locker.isAvailable())
							scheduler.submit(runnable);
					});
				return new ResponseEntity(HttpStatus.OK);
			}else
				return new ResponseEntity(HttpStatus.LOCKED);
		}else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
    }
    

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hellojade(Model model){
        return "hello";
    }

    @RequestMapping(value="/sqlQuery",method=RequestMethod.POST)
    @ResponseBody
    @Transactional
    public  Object sqlQuery(@RequestBody String sql) throws Exception{
        sql = "select * from ("+sql+") as tmp limit 2";
//        SQLQuery query = srv.getSession().createSQLQuery(sql);
//        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//        List<Map<String,Object>> result=query.list();
//
//        if(result.size()>0) {
//            Map<String, Object> row = result.get(0);
//            Map<String, Object> nameTypePair = row.entrySet().stream()
//                    .collect(Collectors.toMap(et -> et.getKey(), et -> et.getValue().getClass().getSimpleName()));
//            result.add(0,nameTypePair);
//        }

        String finalSql = sql;
        List<Object[]> cols = new ArrayList<>();
        srv.getSession().doWork(connection->{
            PreparedStatement ps = connection.prepareStatement(finalSql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            IntStream.range(1, rs.getMetaData().getColumnCount()+1)
                .forEach(i -> {
                    Object[] value=null;
                        try {
                            ResultSetMetaData meta = rs.getMetaData();
                            String name = meta.getColumnName(i);
                            String label = meta.getColumnLabel(i);
                            String className = meta.getColumnClassName(i);
                            int precision = meta.getPrecision(i);
                            int type = meta.getColumnType(i);
                            String typeName = meta.getColumnTypeName(i);
                            int displaySize = meta.getColumnDisplaySize(i);
                            Object v = rs.getObject(i);
                            cols.add(new Object[]{ label, v, className,typeName,precision});
                        } catch (SQLException e) {
                            AppException.throwNew(e);
                        }
                    }
                );
        });
        return cols;
    }

    @RequestMapping("test")
    public void test() {
        AppException.throwDataAccess("hello业务出错了！");
    }

    @RequestMapping("biz")
    public void biz(){
        AppException.throwNew("notBlank.message");
    }



    @RequestMapping("generalError")
//    @ExceptionHandler({Exception.class})
    //@ResponseStatus(value= HttpStatus.I_AM_A_TEAPOT, reason= "I dont know runtime error...")
    public String globalError(Exception e, Model model,HttpServletResponse resp) throws Exception{
        resp.sendError(419,e.getLocalizedMessage());
        if(! (e instanceof GeneralException))
            e = new UnkownException(e);
        model.addAttribute("exception",e);
        return "global/exception";
    }


}
