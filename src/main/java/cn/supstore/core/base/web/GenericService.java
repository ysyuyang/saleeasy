package cn.supstore.core.base.web;


import cn.supstore.biz.util.QueryPage;
import cn.supstore.core.base.hibernate.transformer.CommonTransformer;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by liusijin on 2016/5/23.
 */
@Transactional
public abstract class GenericService<T, PK extends Serializable> implements IService<T, PK> {
    private final Logger log  = LoggerFactory.getLogger(GenericService.class);


    @Resource(type=SessionFactory.class)
    private SessionFactory sessionFactory;

    private Class<T> entityClass;

    @SuppressWarnings({ "unchecked" })
    public GenericService() {
//        this.entityClass = null;
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            if(parameterizedType[0] instanceof Class)
                this.entityClass = (Class<T>) parameterizedType[0];
            else
                log.warn("bad usage ,no type specify, may be runtime error!!!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenericService setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Session getSession(){
        Session session;
        try{
            session = sessionFactory.getCurrentSession();
        }catch (HibernateException e){
            session = sessionFactory.openSession();
            log.info("session leaking !!!!!!!!!!!!!!!!!!{}",session.hashCode());
        }
        return session;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Criteria getCriteria(){
        return getSession().createCriteria(entityClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T load(PK id) {
        return getSession().load(this.entityClass, id);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public T get(PK id) {
        return getSession().get(this.entityClass, id);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> get(PK[] ids) {
        return getSession().createQuery("from "+entityClass.getName()+" as model where model.id in (:ids)").setParameterList("ids", ids).list();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(String propertyName, Object value) {
        return (T) getSession().createQuery("from "+entityClass.getName()+" as model where model."+propertyName+" = :prop").setParameter("prop", value).uniqueResult();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public T get(DetachedCriteria criteria){
        List<T> list = list(criteria);
        return list.size()>0?list.get(0):null;
//      return (T)criteria.getExecutableCriteria(getSession()).uniqueResult();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> getList(String propertyName, Object value) {
        return getSession().createQuery("from "+entityClass.getName()+" as model where model."+propertyName+" = :prop").setParameter("prop", value).list();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getCount() {
        return Integer.parseInt(getCriteria().setProjection(Projections.rowCount()).uniqueResult().toString());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getCount(DetachedCriteria criteria) {
        return getCount(criteria, null);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getCount(DetachedCriteria criteria, String distinct) {
        Projection projection = distinct!=null?Projections.countDistinct(distinct):Projections.rowCount();
        return Integer.parseInt(criteria.setProjection(projection).getExecutableCriteria(getSession()).uniqueResult().toString());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public PK save(T entity) {
        return (PK) getSession().save(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void merge(T entity){
        getSession().merge(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(PK id) {
        getSession().delete(load(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(PK[] ids) {
        for (PK id : ids) {
            delete(id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(List<T> list){
        if(list!=null&&!list.isEmpty()){
            list.forEach(T -> {
                getSession().delete(T);
            });
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> list() {
        return getCriteria().list();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> list(DetachedCriteria criteria) {
        return criteria.getExecutableCriteria(getSession()).list();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Object findObject(DetachedCriteria criteria) {
        return criteria.getExecutableCriteria(getSession()).uniqueResult();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> page(DetachedCriteria criteria, Integer pageSize, Integer page){
        return criteria.getExecutableCriteria(getSession()).setFirstResult((page-1)*pageSize).setMaxResults(pageSize).list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<T> getPage(DetachedCriteria criteria, Page<T> page) {
        return getPage(criteria, page, null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Page<T> getPage(DetachedCriteria criteria, Page<T> page, Projection projection){
        if(page.getKeyWords()!=null&&page.getProperty()!=null&&"".equals(page.getKeyWords().trim())){
            criteria.add(Restrictions.like(page.getProperty(), page.getKeyWords(),page.getMatchMode()));
        }
        page.setTotalCount(getCount(criteria));
        if(projection!=null){
            criteria.setProjection(projection).setResultTransformer(Transformers.aliasToBean(entityClass));
        }else{
            criteria.setProjection(null);
        }
        if(page.getMultiOrder()!=null){
            page.getMultiOrder().forEach(order ->{
                criteria.addOrder(order);
            });
        }
        page.setList(page(criteria, page.getPageSize(), page.getNowPage()));
        return page;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings({ "rawtypes" })
    public List getObjectList(DetachedCriteria criteria) {
        return getObjectList(criteria, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings({ "rawtypes" })
    public List getObjectList(DetachedCriteria criteria, Integer resultCount){
        Criteria c = criteria.getExecutableCriteria(getSession()); if(resultCount!=null)c.setMaxResults(resultCount);
        return c.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public T getByHQL(String hql) {
        return (T) getSession().createQuery(hql).uniqueResult();
    }
    /**
     * 返回一个对象，没有则返回null
     * @param hql
     * @param param
     * @return
     */
    public T getSingleByHQL(String hql,Object[] param) {
    	Query q = getSession().createQuery(hql);
        for(int i=0;i<param.length;i++){
        	q.setParameter(i, param[i]);
        }
        List list = q.list();
        return list.size()>0?(T) list.get(0):null;
    }
    
    public List<T> getByHQL(String hql,Object[] param) {
    	Query q = getSession().createQuery(hql);
        for(int i=0;i<param.length;i++){
        	q.setParameter(i, param[i]);
        }
        return q.list();
    }
    
    public List<T> getByHQL(String hql,Object[] param,QueryPage<T> pg) {
    	Query q = getSession().createQuery(hql);
        for(int i=0;i<param.length;i++){
        	q.setParameter(i, param[i]);
        }
        q.setFirstResult((pg.getNowPage()-1)*pg.getPageSize()).setMaxResults(pg.getPageSize()).list();
        return q.list();
    }
   
    public Page<T> getPageByHQL(String hql, Object[] param,QueryPage<T> pg) {
        Page<T> pager = new Page<T>();
        Query q = getSession().createQuery(hql);
        String countHQL = "select count(*) " + hql.substring(hql.indexOf("from"));
        Query q1 = getSession().createQuery(countHQL);
        for(int i=0;i<param.length;i++){
        	q.setParameter(i, param[i]);
        	q1.setParameter(i, param[i]);
        }
        q.setFirstResult((pg.getNowPage()-1)*pg.getPageSize()).setMaxResults(pg.getPageSize()).list();
        pager.setList(q.list());
        pager.setPageSize(pg.getPageSize());
        pager.setNowPage(pg.getNowPage());
        
        Number total = (Number) q1.uniqueResult();
        pager.setTotalCount(total.intValue());
        pager.setMaxPage((total.intValue()+  pg.getPageSize()  - 1)/pg.getPageSize());
        return pager;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> listByHQL(String hql) {
        return getSession().createQuery(hql).list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public T getBySQL(String sql) {
        return (T) getSession().createSQLQuery(sql).uniqueResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> listBySQL(String sql) {
        return getSession().createSQLQuery(sql).list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("rawtypes")
    public Object getObjectBySQL(String sql, Class clazz) {
        return getSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(clazz)).uniqueResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("rawtypes")
    public List getObjectListBySQL(String sql, Class clazz) {
        return getSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(clazz)).list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Page<T> getPageByHQL(String hql, Integer pageSize, Integer page) {
        Page<T> pager = new Page<T>();
        pager.setList(getSession().createQuery(hql).setFirstResult((page-1)*pageSize).setMaxResults(pageSize).list());
        pager.setPageSize(pageSize);
        pager.setNowPage(page);
        String countHQL = "select count(*) " + hql.substring(hql.indexOf("from"));
        Number total = (Number) getSession().createQuery(countHQL).uniqueResult();
        pager.setTotalCount(total.intValue());
        pager.setMaxPage((total.intValue()+  pageSize  - 1)/pageSize);
        return pager;
    }
    /**
     * 适合普通的分页查询
     * @param sql
     * @param obja
     * @param pageSize
     * @param page
     * @return
     */
    public Page<T> getPageBySQL(String sql,Object[] obja, Integer pageSize, Integer page) {
        Page<T> pager = new Page<T>();
        Query q = getSession().createSQLQuery(sql);
        for(int i=0;i<obja.length;i++){
        	q.setParameter(i, obja[i]);
        }
        if(page!=null&&pageSize!=null){
        	q.setFirstResult((page-1)*pageSize).setMaxResults(pageSize);
        }
        pager.setList(q.list());
        pager.setPageSize(pageSize);
        pager.setNowPage(page);
        String countHQL = "select count(*) " + sql.substring(sql.indexOf("from"));
        Query qcount = getSession().createSQLQuery(countHQL);
        for(int i=0;i<obja.length;i++){
        	qcount.setParameter(i, obja[i]);
        }
        Number total = (Number) qcount.uniqueResult();
        pager.setTotalCount(total==null?0:total.intValue());
        pager.setMaxPage((total.intValue()+  pageSize  - 1)/pageSize);
        return pager;
    }
    /**
     * 用于含有in的查询对集合类型参数的赋值
     * @param sql
     * @param param
     * @param pageSize 
     * @param page
     * @return
     */
    public Page<T> getPageBySQL(String sql,Map<String, Object> param, Integer pageSize, Integer page) {
        Page<T> pager = new Page<T>();
        Query query = getSession().createSQLQuery(sql);
        if (param != null) {  
            Set<String> keySet = param.keySet();  
            for (String string : keySet) {  
                Object obj = param.get(string);  
                //这里考虑传入的参数是什么类型，不同类型使用的方法不同  
                if(obj instanceof Collection<?>){  
                    query.setParameterList(string, (Collection<?>)obj);  
                }else if(obj instanceof Object[]){  
                    query.setParameterList(string, (Object[])obj);  
                }else{  
                    query.setParameter(string, obj);  
                }  
            }  
        } 
        if(page!=null&&pageSize!=null){
        	query.setFirstResult((page-1)*pageSize).setMaxResults(pageSize);
        }
        pager.setList(query.list());
        pager.setPageSize(pageSize);
        pager.setNowPage(page);
        String countHQL = "select count(*) " + sql.substring(sql.indexOf("from"));
        Query qcount = getSession().createSQLQuery(countHQL);
        if (param != null) {  
            Set<String> keySet = param.keySet();  
            for (String string : keySet) {  
                Object obj = param.get(string);  
                //这里考虑传入的参数是什么类型，不同类型使用的方法不同  
                if(obj instanceof Collection<?>){  
                	qcount.setParameterList(string, (Collection<?>)obj);  
                }else if(obj instanceof Object[]){  
                	qcount.setParameterList(string, (Object[])obj);  
                }else{  
                	qcount.setParameter(string, obj);  
                }  
            }  
        }  
        Number total = (Number) qcount.uniqueResult();
        pager.setTotalCount(total==null?0:total.intValue());
        pager.setMaxPage((total.intValue()+  pageSize  - 1)/pageSize);
        return pager;
    }
    
    /**
     * SQL查询并转换为相应对象
     * @param sql
     * @param param
     * @param T.class
     * @param pageSize 
     * @param page
     * @return
     */
    public List<T> QueryObjBySQL(String sql,Object[] param,Class clazz, Integer pageSize, Integer page) {
        Query query = getSession().createSQLQuery(sql).addEntity(clazz);
        if (param != null) {  
        	for(int i=0;i<param.length;i++){
            	query.setParameter(i, param[i]);
            }
        }  
        if(page!=null&&pageSize!=null&&pageSize!=0){
        	query.setFirstResult((page-1)*pageSize).setMaxResults(pageSize);
        }
        return query.list();
    }
    
    public List<T> queryBySQL(String sql,Object[] param,QueryPage pg) {
        Query query = getSession().createSQLQuery(sql);
        if (param != null) {  
        	for(int i=0;i<param.length;i++){
            	query.setParameter(i, param[i]);
            }
        }  
        if(pg.getNowPage()!=null&&pg.getPageSize()!=null&&pg.getPageSize()!=0){
        	query.setFirstResult((pg.getNowPage()-1)*pg.getPageSize()).setMaxResults(pg.getPageSize());
        }
        return query.list();
    }
    
    @Override
    public Page getPageBySQL(String sql, Class clazz, Integer pageSize, Integer page) {
        Page pager = new Page();
        Query query = getSession().createSQLQuery(sql)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .setResultTransformer(CommonTransformer.of(clazz));
        List list = query.list();
        pager.setList(list);
        pager.setPageSize(pageSize);
        pager.setNowPage(page);
        String countSQL = "select count(*) from ( " + sql+") as temp";
        Number total = (Number) getSession().createSQLQuery(countSQL).uniqueResult();
        pager.setTotalCount(total.intValue());
        pager.setMaxPage((total.intValue()+  pageSize  - 1)/pageSize);
        return pager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings({ "rawtypes" })
    public Page getObjectPage(DetachedCriteria criteria, Page page, Projection projection, Class clazz) {
        return getObjectPage(criteria, page, projection, clazz,null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Page getObjectPage(DetachedCriteria criteria, Page page, Projection projection, Class clazz, String distinct){
        if(page.getKeyWords()!=null&&page.getProperty()!=null&&"".equals(page.getKeyWords().trim())){
            criteria.add(Restrictions.like(page.getProperty(), page.getKeyWords(),page.getMatchMode()));
        }
        page.setTotalCount(getCount(criteria,distinct));
        criteria.setProjection(null);
        if(projection!=null){
            criteria.setProjection(projection);
        }
        if(clazz!=null){
            criteria.setResultTransformer(Transformers.aliasToBean(clazz));
        }
        //多字段排序
        if(page.getMultiOrder()!=null){
            page.getMultiOrder().forEach(order ->{
                criteria.addOrder((Order)order);
            });
        }
        page.setList(criteria.getExecutableCriteria(getSession()).setFirstResult((page.getNowPage()-1)*page.getPageSize()).setMaxResults(page.getPageSize()).list());
        return page;
    }
    
    public Integer executeBySQL(String sql,Object[] param) {
    	Query q = getSession().createSQLQuery(sql);
        for(int i=0;i<param.length;i++){
        	q.setParameter(i, param[i]);
        }
        return q.executeUpdate();
    }
    public Integer executeBySQL(String sql) {
    	Query q = getSession().createSQLQuery(sql);
        return q.executeUpdate();
    }
    public Integer executeByHQL(String hql,Object[] param) {
    	Query q = getSession().createQuery(hql);
        for(int i=0;i<param.length;i++){
        	q.setParameter(i, param[i]);
        }
        return q.executeUpdate();
    }
    public List queryBySQL(String sql,Object[] param) {
    	Query q = getSession().createSQLQuery(sql);
        for(int i=0;i<param.length;i++){
        	q.setParameter(i, param[i]);
        }
        return q.list();
    }
    public List queryMapListBySQL(String sql,Object[] param) {
    	Query q = getSession().createSQLQuery(sql);
        for(int i=0;i<param.length;i++){
        	q.setParameter(i, param[i]);
        }
        return q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }
}
