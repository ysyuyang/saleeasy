package cn.supstore.core.base.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liusijin on 2016/5/23.
 */
public class Page<T> {

    public enum OrderType{
        asc,desc
    }

    private static final Integer MAX_PAGE_SIZE = 500;//分页允许最大的尺寸

    private List<T> list;//分页数据

    @QueryParam("pageSize")
    @DefaultValue("5")
    private Integer pageSize=5;//分页大小
    @QueryParam("nowPage")
    @DefaultValue("1")
    private Integer nowPage=1;//分页页码

    @JsonIgnore
    private List<Order> multiOrder = new ArrayList<Order>();//多列排序

    private Integer totalCount=0;//总条数

    private Integer maxPage=0;//总页数

    @JsonIgnore
    private String property;//关键字检索字段名称

    @JsonIgnore
    private String keyWords;//关键字检索关键字

    @JsonIgnore
    private MatchMode matchMode= MatchMode.ANYWHERE;//关键字检索的匹配模式

    public Page<T> addOrder(Order order){
        this.multiOrder.add(order);
        return this;
    }

    public List<T> getList() {
        return list;
    }
    public void setList(List<T> list) {
        this.list = list;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize<1?1:pageSize>MAX_PAGE_SIZE?MAX_PAGE_SIZE:pageSize;
    }
    public Integer getNowPage() {
        return nowPage;
    }
    public void setNowPage(Integer nowPage) {
        this.nowPage = nowPage;
    }
    public Integer getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
    public boolean hasNext(){
        return totalCount>pageSize*nowPage?true:false;
    }
    public Integer getMaxPage() {
        this.maxPage = this.totalCount/this.pageSize;
        return this.totalCount%this.pageSize>0?++this.maxPage:this.maxPage;
    }
    public void setMaxPage(Integer maxPage) {
        this.maxPage = maxPage;
    }
    public String getProperty() {
        return property;
    }
    public void setProperty(String property) {
        this.property = property;
    }
    public String getKeyWords() {
        return keyWords;
    }
    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }
    public MatchMode getMatchMode() {
        return matchMode;
    }
    public void setMatchMode(MatchMode matchMode) {
        this.matchMode = matchMode;
    }
    public List<Order> getMultiOrder() {
        return multiOrder;
    }
    public void setMultiOrder(List<Order> multiOrder) {
        this.multiOrder = multiOrder;
    }
    public Page() {
        super();
    }
}
