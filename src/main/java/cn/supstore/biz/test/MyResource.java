package cn.supstore.biz.test;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Api("测试")
@Path("/resource")
@Controller
public class MyResource
{
   private static int count = 0;


   @POST
   @Produces("text/plain")
   @Consumes("text/plain")
   public String post(String content) throws Exception
   {
      Thread.sleep(1000);
      return content;
   }

   @GET
   @Produces("text/plain")
   public String get()
   {
      return Integer.toString(count);
   }

   @PUT
   @Consumes("text/plain")
   public Response put(String content) throws Exception
   {
      System.out.println("IN PUT!!!!");
      Thread.sleep(1000);
      System.out.println("******* countdown complete ****");
      count++;
      return Response.ok("******* countdown complete ****").build();
   }
}
