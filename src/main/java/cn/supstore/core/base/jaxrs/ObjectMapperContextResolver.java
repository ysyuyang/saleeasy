package cn.supstore.core.base.jaxrs;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Created by liusijin on 2016/5/23.
 */
@Provider
@Component
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {
    private final ObjectMapper MAPPER;

    public ObjectMapperContextResolver() {
        MAPPER = new ObjectMapper();
        MAPPER.registerModule(new JavaTimeModule());
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        MAPPER.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,true);

//        MAPPER =  Jackson2ObjectMapperBuilder.json()
//                .serializationInclusion(JsonInclude.Include.NON_NULL) // Don’t include null values
//                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) //ISODate
//                .featuresToDisable(SerializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
//                .modules(new JavaTimeModule())
//                .build();
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return MAPPER;
    }
}