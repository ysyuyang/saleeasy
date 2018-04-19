package cn.supstore.core.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import cn.supstore.core.base.interceptor.LoginInterceptor;
import de.neuland.jade4j.JadeConfiguration;
import de.neuland.jade4j.spring.template.SpringTemplateLoader;
import de.neuland.jade4j.spring.view.JadeViewResolver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;
   
@Configuration
@EnableWebMvc
@Import(SwaggerConfig.class)
@ComponentScan(basePackages = { "cn.supstore.biz", "cn.supstore.core"})
class WebMvcConfig extends WebMvcConfigurerAdapter{

    private static final String MESSAGE_SOURCE = "/WEB-INF/i18n/messages";
    private static final String VIEWS = "/WEB-INF/views/";

    private static final String RESOURCES_LOCATION = "/assets/";
    private static final String RESOURCES_HANDLER = RESOURCES_LOCATION + "**";
    
    private static final String JSPRESOURCES_LOCATION = "/WEB-INF/res/";
    private static final String JSPRESOURCES_HANDLER = "/res/**";
    
    private static final String CSSRESOURCES_LOCATION = "/WEB-INF/css/";
    private static final String CSSRESOURCES_HANDLER = "/css/**";
    
    private static final String JSRESOURCES_LOCATION = "/WEB-INF/js/";
    private static final String JSRESOURCES_HANDLER = "/js/**";
    
    private static final String IMGRESOURCES_LOCATION = "/WEB-INF/image/";
    private static final String IMGRESOURCES_HANDLER = "/image/**";
    
    @Autowired
    Environment env;

    @Bean(name = "messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(MESSAGE_SOURCE);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setCacheSeconds(5);
        return messageSource;
    }



    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.json()
                .serializationInclusion(JsonInclude.Include.NON_NULL) // Don’t include null values
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) //ISODate
                .featuresToDisable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .featuresToEnable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                .modules(new JavaTimeModule());
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
       /* JadeViewResolver jadeResolver = new JadeViewResolver();
        JadeConfiguration jadeConf = new JadeConfiguration();
        jadeConf.setCaching(true);
        jadeConf.setPrettyPrint(true);
        jadeConf.setTemplateLoader(getJadeLoader());
        jadeResolver.setConfiguration(jadeConf);
        jadeResolver.setRequestContextAttribute("rc");
        jadeResolver.setOrder(0);
        registry.viewResolver(jadeResolver);
*/

        InternalResourceViewResolver jspResolver = new InternalResourceViewResolver();
        jspResolver.setViewClass(JstlView.class);
        jspResolver.setPrefix("/WEB-INF/views/");
        jspResolver.setSuffix(".jsp");
        jspResolver.setRequestContextAttribute("rc");
        jspResolver.setOrder(10);
        registry.jsp(VIEWS, ".jsp");
        registry.viewResolver(jspResolver);
    }

    /*@Bean
    public SpringTemplateLoader getJadeLoader(){
        SpringTemplateLoader templateLoader = new SpringTemplateLoader();
        templateLoader.setBasePath("/WEB-INF/views/");
        templateLoader.setEncoding("UTF-8");
        templateLoader.setSuffix(".jade");
        return templateLoader;
    }*/

    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(RESOURCES_HANDLER)
                .addResourceLocations(RESOURCES_LOCATION);
       
        registry.addResourceHandler(JSPRESOURCES_HANDLER)
				.addResourceLocations(JSPRESOURCES_LOCATION);

		registry.addResourceHandler(JSRESOURCES_HANDLER)
				.addResourceLocations(JSRESOURCES_LOCATION);
		
		registry.addResourceHandler(CSSRESOURCES_HANDLER)
				.addResourceLocations(CSSRESOURCES_LOCATION);
		
		registry.addResourceHandler(IMGRESOURCES_HANDLER)
				.addResourceLocations(IMGRESOURCES_LOCATION);

		registry.addResourceHandler("/html/**")
		        .addResourceLocations("/html/");
		registry.addResourceHandler("/static/css/**")
				.addResourceLocations("/css/")
				.setCachePeriod(31556926);
		registry.addResourceHandler("/static/img/**")
				.addResourceLocations("/img/")
				.setCachePeriod(31556926);
		registry.addResourceHandler("/static/js/**")
				.addResourceLocations("/js/")
				.setCachePeriod(31556926);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/doc", "/assets/swagger/index.html");
        registry.addRedirectViewController("/doc/", "/assets/swagger/index.html");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**");
//                .excludePathPatterns(
//                        "/robots.txt");
    }
}
