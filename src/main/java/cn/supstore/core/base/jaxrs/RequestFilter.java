package cn.supstore.core.base.jaxrs;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by liusijin on 2017/5/19.
 */
@Provider
@PreMatching
@Component
public class RequestFilter implements ContainerRequestFilter {
    @Context
    HttpServletRequest req;
    
   
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        req.setAttribute("timer", System.nanoTime());
    }
}
