package cn.supstore.core.base.web;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liusijin on 2016/5/23.
 */
public interface IService<T, PK extends Serializable> {
    IService setEntityClass(Class<T> entityClass);

    Session getSession();

    Criteria getCriteria();

    T load(PK id);

    T get(PK id);

    @SuppressWarnings("unchecked")
    List<T> get(PK[] ids);

    @SuppressWarnings("unchecked")
    T get(String propertyName, Object value);

    T get(DetachedCriteria criteria);

    @SuppressWarnings("unchecked")
    List<T> getList(String propertyName, Object value);

    Integer getCount();

    Integer getCount(DetachedCriteria criteria);

    Integer getCount(DetachedCriteria criteria, String distinct);

    @SuppressWarnings("unchecked")
    PK save(T entity);

    void saveOrUpdate(T entity);

    void update(T entity);

    void merge(T entity);

    void delete(T entity);

    void delete(PK id);

    void delete(PK[] ids);

    void delete(List<T> list);

    @SuppressWarnings("unchecked")
    List<T> list();

    @SuppressWarnings("unchecked")
    List<T> list(DetachedCriteria criteria);

    Object findObject(DetachedCriteria criteria);

    @SuppressWarnings("unchecked")
    List<T> page(DetachedCriteria criteria, Integer pageSize, Integer pageNumber);

    Page<T> getPage(DetachedCriteria criteria, Page<T> page);

    Page<T> getPage(DetachedCriteria criteria, Page<T> page, Projection projection);

    @SuppressWarnings({ "rawtypes" })
    List getObjectList(DetachedCriteria criteria);

    @SuppressWarnings({ "rawtypes" })
    List getObjectList(DetachedCriteria criteria, Integer resultCount);

    @SuppressWarnings("unchecked")
    T getByHQL(String hql);

    @SuppressWarnings("unchecked")
    List<T> listByHQL(String hql);

    @SuppressWarnings("unchecked")
    T getBySQL(String sql);

    @SuppressWarnings("unchecked")
    List<T> listBySQL(String sql);

    @SuppressWarnings("rawtypes")
    Object getObjectBySQL(String sql, Class clazz);

    @SuppressWarnings("rawtypes")
    List getObjectListBySQL(String sql, Class clazz);

    @SuppressWarnings("unchecked")
    Page<T> getPageByHQL(String hql, Integer pageSize, Integer page);
    
     
     

    @SuppressWarnings("unchecked")
    Page getPageBySQL(String sql, Class clazz, Integer pageSize, Integer page);
    
 

    @SuppressWarnings({ "rawtypes" })
    Page getObjectPage(DetachedCriteria criteria, Page page, Projection projection, Class clazz);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    Page getObjectPage(DetachedCriteria criteria, Page page, Projection projection, Class clazz, String distinct);
}
