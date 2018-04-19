package cn.supstore.biz.api;

import cn.supstore.biz.model.TmEvent;
import cn.supstore.core.base.web.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by liusijin on 2016/5/23.
 */
@Api("事件查询")
@Path("event")
public interface IEvent {

    @GET
    @ApiOperation(
            value = "查询最近的事件",
            notes = "时间倒排序",
            response = TmEvent.class,
            responseContainer = "List")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TmEvent> list();

    @GET
    @Path("/page")
    @Produces(MediaType.APPLICATION_JSON)
    Page<?> pageBy(@BeanParam Page page);

    @Path("{eventId}")
    @GET
    @ApiOperation(
            value = "ID查询事件",
            notes = "时间倒排序",
            response = TmEvent.class)
    @Produces(MediaType.APPLICATION_JSON)
    public TmEvent getById(@NotNull @PathParam("eventId") String id);

    @Path("page")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Page<TmEvent> pagedEvent(@BeanParam Page page) ;

    @SuppressWarnings("RestParamTypeInspection")
    @Path("segment/{from}/{to}")
    @GET
    @ApiOperation(
            value = "时间段内事件",
            notes = "时间倒排序",
            response = TmEvent.class,
            responseContainer = "List")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TmEvent> segment(@PathParam("from") LocalDateTime fromTime, @PathParam("to") LocalDateTime toTime);


    @POST
    @ApiOperation(
            value = "创建事件",
            notes = "返回ID",
            response = TmEvent.class,
            responseContainer = "String UUID")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TmEvent create(TmEvent event);

    @DELETE
    @Path("/{eventId}")
    @ApiOperation(
            value = "删除事件",
            notes = "返回删除事件",
            response = TmEvent.class)
    @Produces(MediaType.APPLICATION_JSON)
    public TmEvent delete(@NotNull @PathParam("eventId") String id);

}
