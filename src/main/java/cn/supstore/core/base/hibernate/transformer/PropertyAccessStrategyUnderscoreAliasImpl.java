package cn.supstore.core.base.hibernate.transformer;

import org.hibernate.property.access.spi.PropertyAccess;
import org.hibernate.property.access.spi.PropertyAccessStrategy;

/**
 * Created by liusijin on 16/5/29.
 */
public class PropertyAccessStrategyUnderscoreAliasImpl  implements PropertyAccessStrategy {
        /**
         * Singleton access
         */
        public static final PropertyAccessStrategyUnderscoreAliasImpl INSTANCE = new PropertyAccessStrategyUnderscoreAliasImpl();

        @Override
        public PropertyAccess buildPropertyAccess(Class containerJavaType, String propertyName) {
            return new UnderscoreAccessFieldImpl( this, containerJavaType, propertyName );
        }
}
