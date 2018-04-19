package cn.supstore.core.base.web;

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
import java.util.List;

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
    
    public List<T> getPageByHQL(String hql, Object[] params) {
    	Query q = getSession().createQuery(hql);
    	int len = 0;
    	if(params != null && (len = params.length)>0) {
    		if(len == 1) {
    			q.setParameter(0, params[0]);
    		} else {
    			for (int i = 0; i<len; i++) {
    				q.setParameter(i, params[i]);
    			}
    		}
    	}
    	List<T> list = q.list();
        return list;
    }
    
    public Page<T> getPageByHQL(String hql, Integer pageSize, Integer page, Object[] params) {
    	Page<T> pager = new Page<T>();
    	Query q = getSession().createQuery(hql);
    	String countHQL = "select count(*) " + hql.substring(hql.indexOf("from"));
        Query q1 = getSession().createQuery(countHQL);
    	int len = 0;
    	if(params != null && (len = params.length)>0) {
    		if(len == 1) {
    			q.setParameter(0, params[0]);
    			q1.setParameter(0, params[0]);
    		} else {
    			for (int i = 0; i<len; i++) {
    				q.setParameter(i, params[i]);
    				q1.setParameter(i, params[i]);
    			}
    		}
    	}
    	List<T> list = q.setFirstResult((page-1)*pageSize).setMaxResults(pageSize).list();
    	pager.setList(list);
    	pager.setPageSize(pageSize);
        pager.setNowPage(page);
        Number total = (Number) q1.uniqueResult();
        pager.setTotalCount(total.intValue());
        pager.setMaxPage((total.intValue()+  pageSize  - 1)/pageSize);
        return pager;
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
    
    public Page getPageBySQL(String sql, Class clazz, Integer pageSize, Integer page, Object[] params) {
    	Page<T> pager = new Page<T>();
    	Query q = getSession().createSQLQuery(sql);
    	int len = 0;
    	if(params != null && (len = params.length)>0) {
    		if(len == 1) {
    			q.setParameter(0, params[0]);
    		} else {
    			for (int i = 0; i<len; i++) {
    				q.setParameter(i, params[i]);
    			}
    		}
    	}
    	if(clazz != null) {
    		q.setResultTransformer(CommonTransformer.of(clazz));
    	}
    	List<T> list = q.setFirstResult((page-1)*pageSize).setMaxResults(pageSize).list();
    	pager.setList(list);
    	pager.setPageSize(pageSize);
        pager.setNowPage(page);
        String countSQL = "select count(*) from ( " + sql+") as temp";
        Query q1 = getSession().createSQLQuery(countSQL);
        if(params != null && (len = params.length)>0) {
    		if(len == 1) {
    			q1.setParameter(0, params[0]);
    		} else {
    			for (int i = 0; i<len; i++) {
    				q1.setParameter(i, params[i]);
    			}
    		}
    	}
        Number total = (Number) q1.uniqueResult();
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

}
