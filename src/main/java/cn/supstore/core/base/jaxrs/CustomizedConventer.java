package cn.supstore.core.base.jaxrs;


import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

/**
 * Created by liusijin on 2016/5/23.
 */
@Provider
@Component
public class CustomizedConventer implements ParamConverterProvider {

        private final LocalDateTimeConverter converter = new LocalDateTimeConverter();

        @Override
        public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
            if (!rawType.equals(LocalDateTime.class)) return null;
            return (ParamConverter<T>) converter;
        }

        public class LocalDateTimeConverter implements ParamConverter<LocalDateTime> {

            public LocalDateTime fromString(String value) {
                if (value == null ||value.isEmpty()) return null; // change this for production
                return LocalDateTime.parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }

            public String toString(LocalDateTime value) {
                if (value == null) return "";
                return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(value);
            }

        }
    }
