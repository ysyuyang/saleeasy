package cn.supstore.core.base.hibernate.transformer;

import cn.supstore.core.base.exception.AppException;
import org.hibernate.internal.util.ReflectHelper;
import org.hibernate.property.access.spi.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * JDBC数据映射到Bean
 * 支持下划线拼接字段转换为驼峰字段: 如
 *      sex_name_you_has => sexNameYouHas
 */
public class UnderscoreAccessFieldImpl implements PropertyAccess {
    private final PropertyAccessStrategyUnderscoreAliasImpl strategy;
    private final Getter getter;
    private final Setter setter;
    private  String camelName;

    public UnderscoreAccessFieldImpl(
            PropertyAccessStrategyUnderscoreAliasImpl strategy,
            Class containerJavaType,
            final String propertyName) {
        this.strategy = strategy;

        String[] piceis = propertyName.split("_");
        if(!(piceis.length>1))
            AppException.throwDataAccess("parameter in class bean is not a underscore field mapping");
        else{//如果下划线后只有一个字符,可能超出数组
            camelName = Arrays.stream(piceis).map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                    .collect(Collectors.joining());
            camelName = camelName.substring(0,1).toLowerCase()+ camelName.substring(1);
        }
        final Field field = ReflectHelper.findField( containerJavaType, camelName );
        this.getter = new GetterFieldImpl( containerJavaType, camelName, field );
        this.setter = new SetterFieldImpl( containerJavaType, camelName, field );
    }

    @Override
    public PropertyAccessStrategy getPropertyAccessStrategy() {
        return strategy;
    }

    @Override
    public Getter getGetter() {
        return getter;
    }

    @Override
    public Setter getSetter() {
        return setter;
    }

}
