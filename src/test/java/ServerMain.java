///**
// * Created by liusijin on 16/4/10.
// */
//import org.eclipse.jetty.annotations.AnnotationConfiguration;
//import org.eclipse.jetty.http2.server.HTTP2CServerConnectionFactory;
//import org.eclipse.jetty.plus.webapp.EnvConfiguration;
//import org.eclipse.jetty.plus.webapp.PlusConfiguration;
//import org.eclipse.jetty.server.*;
//import org.eclipse.jetty.server.handler.ContextHandler;
//import org.eclipse.jetty.server.handler.HandlerList;
//import org.eclipse.jetty.server.handler.ResourceHandler;
//import org.eclipse.jetty.util.resource.PathResource;
//import org.eclipse.jetty.webapp.*;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//
///**
// * Created by liusijin on 16/4/5.
// */
//public class ServerMain {
//    public static void main(String args[]) throws Exception {
//        Server server = new Server();
//
//        HttpConfiguration config = new HttpConfiguration();
//        HttpConnectionFactory h1 = new HttpConnectionFactory(config);
//        HTTP2CServerConnectionFactory h2 = new HTTP2CServerConnectionFactory(config);
//
//        ServerConnector http = new ServerConnector(server,h1,h2);
//        http.setPort(8080);
//        http.setIdleTimeout(30000);
//        server.addConnector(http);
//
//        Handler jee = createApp("/shopguide-GJ");
//        // Handler assets = resourceContext("/connectivity");
//
//        HandlerList handlers = new HandlerList();
//        handlers.setHandlers(new Handler[] { jee });// assets,jee
//        server.setHandler(handlers);
//        server.start();
////        server.dump(System.err);
//        server.join();
//    }
//
//    private static Handler createApp(String path) throws IOException {
//        File devEnvDir = new File("src/main/webapp/");
//        String webRootPath;
//        if (devEnvDir.exists())
//            webRootPath = devEnvDir.toPath().toRealPath().toString();
//        else
//            webRootPath = cn.supstore.tool.ServerMain.class.getProtectionDomain().getCodeSource().getLocation().toExternalForm();
//
//        WebAppContext webapp = new WebAppContext(webRootPath, path);
//        System.err.println("current Web path is :" + webRootPath);
//
//
//
//        webapp.setConfigurations(new Configuration[]
//                {
//                        new AnnotationConfiguration(),
//                        new WebInfConfiguration(),
//                        new WebXmlConfiguration(),
//                        new MetaInfConfiguration(),
//                        new FragmentConfiguration(),
//                        new EnvConfiguration(),
//                        new PlusConfiguration(),
//                        new JettyWebXmlConfiguration()
//                });
//        webapp.setAttribute(AnnotationConfiguration.SERVLET_CONTAINER_INITIALIZER_EXCLUSION_PATTERN,
//                "^(?!org.springframework.web|cn.supstore).*\\$");
//        webapp.setConfigurationDiscovered(true);
//
//        String className = cn.supstore.tool.ServerMain.class.getName().replace('.', '/');
//        String classJar = cn.supstore.tool.ServerMain.class.getResource("/" + className + ".class").toString();
//        if (classJar.startsWith("jar:")) {
//            webapp.setAttribute(WebInfConfiguration.CONTAINER_JAR_PATTERN,
//                    ".*uber[^/]*\\.jar$");
//        }else{
//            webapp.setAttribute(WebInfConfiguration.CONTAINER_JAR_PATTERN,
//            		".*/spring-web[^/]*\\.jar$|.*/classes/.*|.*/bin/.*");
//        }
//
//        // CodeSource codeSource = ServerMain.class.getProtectionDomain().getCodeSource();
//        // System.err.println("====================>" + codeSource);
//        // URL classes = codeSource.getLocation();
//        // System.err.println("====================>" + classes);
//        // webapp.getMetaData().setWebInfClassesDirs(
//        // Arrays.asList(Resource.newResource(classes)));
//
//        webapp.setParentLoaderPriority(true);
//        webapp.setWelcomeFiles(new String[] { "index.html" });
//        return webapp;
//    }
//
//    private static Handler resourceContext(String path) throws Exception {
//        URL warPath = cn.supstore.tool.ServerMain.class.getProtectionDomain().getCodeSource().getLocation();
//        ContextHandler staticCtx = new ContextHandler(path);
//
//        ResourceHandler resource_handler = new ResourceHandler();
//        System.err.println("resources root path is :" + warPath);
//        resource_handler.setBaseResource(new PathResource(warPath.toURI()));
//
//        resource_handler.setDirectoriesListed(true);
//        resource_handler.setWelcomeFiles(new String[] { "index.html" });
//        staticCtx.setHandler(resource_handler);
//        return staticCtx;
//    }
//}
//
