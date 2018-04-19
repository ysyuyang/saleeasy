package cn.supstore.biz.util;


import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;


public class QueryPage<T> {


    @QueryParam("pageSize")
    @DefaultValue("10")
    private Integer pageSize=10;//分页大小
    @QueryParam("nowPage")
    @DefaultValue("1")
    private Integer nowPage=1;//分页页码
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getNowPage() {
		return nowPage;
	}
	public void setNowPage(Integer nowPage) {
		this.nowPage = nowPage;
	}

    
    
}
