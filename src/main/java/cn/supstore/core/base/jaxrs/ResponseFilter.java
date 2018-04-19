package cn.supstore.core.base.jaxrs;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

import cn.supstore.core.base.AppUtil;

/**
 * Created by liusijin on 2016/5/24.
 */
@Provider
@Component
class ResponseFilter implements ContainerResponseFilter{

    @Context
    HttpServletRequest req;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        Principal userPrincipal = req.getUserPrincipal();
        HttpSession session = req.getSession(false);
        long start = (long)req.getAttribute("timer");
        AppUtil.getCtx().publishEvent(new ServletRequestHandledEvent(this,
                requestContext.getUriInfo().getPath(),req.getRemoteAddr(),
                requestContext.getMethod(),requestContext.getUriInfo().getBaseUri().toString(),
                session==null?"":session.getId(),userPrincipal==null?"":userPrincipal.getName(),
                (System.nanoTime()-start)/1000000,null,responseContext.getStatus()));
    }
}