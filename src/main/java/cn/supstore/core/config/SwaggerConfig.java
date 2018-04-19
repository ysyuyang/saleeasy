package cn.supstore.core.config;

/**
 * Created by liusijin on 2016/5/17.
 */

import com.fasterxml.classmate.TypeResolver;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket storeApi() {
        ResponseMessage rsp500 = new ResponseMessageBuilder()
                .code(500)
                .message("500 message")
                .responseModel(new ModelRef("Error"))
                .build();

        return new Docket(DocumentationType.SPRING_WEB)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(apiInfo())
                .directModelSubstitute(LocalDate.class,String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .alternateTypeRules(
                        newRule(typeResolver.resolve(DeferredResult.class,
                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                                typeResolver.resolve(WildcardType.class)))
                .useDefaultResponseMessages(false)
//                .globalResponseMessage(RequestMethod.GET, Arrays.asList(rsp500))
                .enableUrlTemplating(true)
//                .globalOperationParameters(
//                        Arrays.asList(new ParameterBuilder()
//                                .name("someGlobalParameter")
//                                .description("Description of someGlobalParameter")
//                                .modelRef(new ModelRef("string"))
//                                .parameterType("query")
//                                .required(true)
//                                .build()))
                .tags(new Tag("Store MVC Service", "All apis"));
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("小易", "SPING-MVC", "1.0", "urn:tos",
                new Contact("yuyang", "", "mm"), "shopguide-GJ © 2005-2016  ", "http://www.supstore.cn/licenses");
        return apiInfo;
    }

    @Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration(
                "validatorUrl",// url
                "none",       // docExpansion          => none | list
                "alpha",      // apiSorter             => alpha
                "http",     // defaultModelRendering => schema
                false,        // enableJsonEditor      => true | false
                true);        // showRequestHeaders    => true | false
    }

    @Bean
    BeanConfig jaxrsConfig(){
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        // beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/saleeasy/api");
        beanConfig.setResourcePackage("cn.supstore");
        beanConfig.setScan(true);
        return beanConfig;
    }

}
