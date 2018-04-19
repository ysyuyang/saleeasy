package cn.supstore.core.config;

import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;
import org.jboss.resteasy.plugins.server.servlet.ResteasyContextParameters;
import org.jboss.resteasy.plugins.validation.ValidatorContextResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.IntrospectorCleanupListener;

import cn.supstore.core.base.module.LocalDateTimeModule;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;

@Configuration
@PropertySource(value={"classpath:application.properties"})
@EnableScheduling
public class ApplicationConfig implements ApplicationListener, WebApplicationInitializer {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        logger.info(event.toString());
    }

    /**
     * 注册MVC类
     * 注册JAXRS 启动
     * @param context
     * @throws ServletException
     */
    @Override
    public void onStartup(ServletContext context) throws ServletException {

        // 根context，加载必要的bean
        AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();
        springContext.register(DataBaseConfig.class,ApplicationConfig.class,WebMvcConfig.class, RedisConfig.class);

        springContext.scan("cn.supstore.base","cn.supstore.biz");
        context.setInitParameter(ResteasyContextParameters.RESTEASY_SERVLET_MAPPING_PREFIX,"/api");
        context.addListener(ResteasyBootstrap.class);
        context.addListener(new ResteasySpringListener(springContext));
        context.addListener(IntrospectorCleanupListener.class);


        // springmvc context

        ServletRegistration.Dynamic jaxrs = context.addServlet("jarxRsDispatch",new HttpServletDispatcher());
        jaxrs.setLoadOnStartup(1);
        jaxrs.addMapping("/api/*");

        ServletRegistration.Dynamic springmvc =
                context.addServlet("springDispatch", new DispatcherServlet(springContext));
        springmvc.setLoadOnStartup(2);
        springmvc.addMapping("/");


        FilterRegistration encodeFilter = context.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
        encodeFilter.setInitParameter("encoding", "UTF-8");
        encodeFilter.setInitParameter("forceEncoding", "true");
        encodeFilter.addMappingForUrlPatterns(null, false, "/*");
        context.addFilter("corsFilter", configCorsForRestApi()).addMappingForUrlPatterns(null, false, "/api/*");

        final Map<String, ? extends ServletRegistration> map = context.getServletRegistrations();
        for (String key : map.keySet()) {
            context.log("Registered Servlet: ======>" + map.get(key).getName());
        }
    }
    
    CorsFilter configCorsForRestApi(){
		CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList(CrossOrigin.DEFAULT_ORIGINS));
    configuration.setAllowedMethods(Arrays.asList("OPTIONS", "GET", "POST", "DELETE", "PUT", "PATCH"));
    configuration.setAllowedHeaders(Arrays.asList(CrossOrigin.DEFAULT_ALLOWED_HEADERS));
    configuration.setAllowCredentials(CrossOrigin.DEFAULT_ALLOW_CREDENTIALS);
    configuration.setMaxAge(CrossOrigin.DEFAULT_MAX_AGE);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

	CorsFilter corsFilter = new CorsFilter(source);
    return corsFilter;
}


    @Bean
    ApiListingResource swaggerRoot(){
        return new ApiListingResource();
    }

    @Bean
    SwaggerSerializers swaggerSerial(){
        return new SwaggerSerializers();
    }

    @Bean
    ValidatorContextResolver beanValidatorForRestEasy(){
        return new ValidatorContextResolver();
    }
    
    @Bean(name="mapper")
    public static ObjectMapper getMapper() {
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.registerModule(new LocalDateTimeModule(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        mapper.registerModule(new JavaTimeModule());
    	return mapper;
    }

}