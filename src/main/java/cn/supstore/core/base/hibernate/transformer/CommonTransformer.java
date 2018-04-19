package cn.supstore.core.base.hibernate.transformer;

import org.hibernate.HibernateException;
import org.hibernate.property.access.internal.PropertyAccessStrategyBasicImpl;
import org.hibernate.property.access.internal.PropertyAccessStrategyChainedImpl;
import org.hibernate.property.access.internal.PropertyAccessStrategyFieldImpl;
import org.hibernate.property.access.internal.PropertyAccessStrategyMapImpl;
import org.hibernate.property.access.spi.Setter;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;

import java.util.Arrays;

/**
 * Created by liusijin on 16/5/29.
 */
public class CommonTransformer  extends AliasedTupleSubsetResultTransformer {

    private final Class resultClass;
    private boolean isInitialized;
    private String[] aliases;
    private Setter[] setters;

    public CommonTransformer(Class resultClass) {
        if ( resultClass == null ) {
            throw new IllegalArgumentException( "resultClass cannot be null" );
        }
        isInitialized = false;
        this.resultClass = resultClass;
    }

    public final static CommonTransformer of(Class resultClass){
        return new CommonTransformer(resultClass);
    }

    @Override
    public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
        return false;
    }

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        Object result;

        try {
            if ( ! isInitialized ) {
                initialize( aliases );
            }
            else {
                check( aliases );
            }

            result = resultClass.newInstance();

            for ( int i = 0; i < aliases.length; i++ ) {
                if ( setters[i] != null ) {
                    setters[i].set( result, tuple[i], null );
                }
            }
        }
        catch ( InstantiationException e ) {
            throw new HibernateException( "Could not instantiate resultclass: " + resultClass.getName() );
        }
        catch ( IllegalAccessException e ) {
            throw new HibernateException( "Could not instantiate resultclass: " + resultClass.getName() );
        }

        return result;
    }

    private void initialize(String[] aliases) {
        PropertyAccessStrategyChainedImpl propertyAccessStrategy = new PropertyAccessStrategyChainedImpl(
                PropertyAccessStrategyBasicImpl.INSTANCE,
                PropertyAccessStrategyFieldImpl.INSTANCE,
                PropertyAccessStrategyUnderscoreAliasImpl.INSTANCE,
                PropertyAccessStrategyMapImpl.INSTANCE
        );
        this.aliases = new String[ aliases.length ];
        setters = new Setter[ aliases.length ];
        for ( int i = 0; i < aliases.length; i++ ) {
            String alias = aliases[ i ];
            if ( alias != null ) {
                this.aliases[ i ] = alias;
                setters[ i ] = propertyAccessStrategy.buildPropertyAccess( resultClass, alias ).getSetter();
            }
        }
        isInitialized = true;
    }

    private void check(String[] aliases) {
        if ( ! Arrays.equals( aliases, this.aliases ) ) {
            throw new IllegalStateException(
                    "aliases are different from what is cached; aliases=" + Arrays.asList( aliases ) +
                            " cached=" + Arrays.asList( this.aliases ) );
        }
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }

        CommonTransformer that = ( CommonTransformer ) o;

        if ( ! resultClass.equals( that.resultClass ) ) {
            return false;
        }
        if ( ! Arrays.equals( aliases, that.aliases ) ) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = resultClass.hashCode();
        result = 31 * result + ( aliases != null ? Arrays.hashCode( aliases ) : 0 );
        return result;
    }
}
