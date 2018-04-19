package cn.supstore.core.config;

import cn.supstore.core.base.hibernate.PhysicalNamingStrategyImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyHbmImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

/**
 * Created by liusijin on 2016/5/23.
 */
@Configurable
@PropertySource(value={"classpath:hibernate.properties"})
@EnableTransactionManagement(proxyTargetClass=true)
public class DataBaseConfig {

    @Autowired
    Environment env;

    @Bean(name="DataSource")
    public DataSource dataSource(){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(env.getRequiredProperty("dataSource.driverClassName"));
        config.setJdbcUrl(env.getRequiredProperty("dataSource.url"));
        config.setUsername(env.getRequiredProperty("dataSource.username"));
        config.setPassword(env.getRequiredProperty("dataSource.password"));
        config.setMaximumPoolSize(Integer.valueOf(env.getRequiredProperty("dataSource.maximumPoolSize")));

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");

        return new HikariDataSource(config);
    }

    @Bean(name="sessionFactory")
    @Autowired
    public SessionFactory  sessionFactory(DataSource ds){
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(ds);
        builder.scanPackages("cn.supstore.biz.model","cn.supstore.biz.infotrain.model","cn.supstore.biz.work.model", "cn.supstore.biz.servicehall.model","cn.supstore.biz.trial.model")
                .addProperties(hibernateProperties());
        builder.setImplicitNamingStrategy(new ImplicitNamingStrategyLegacyHbmImpl());
        builder.setPhysicalNamingStrategy(new PhysicalNamingStrategyImpl());
        return builder.buildSessionFactory();

    }

    private Properties hibernateProperties() {
        Properties prop = new Properties();
        prop.put(DIALECT, env.getRequiredProperty("hibernate.dialect"));
        prop.put(SHOW_SQL, env.getRequiredProperty("hibernate.show_sql"));
        prop.put(FORMAT_SQL, env.getRequiredProperty("hibernate.format_sql"));
        prop.put(HBM2DDL_AUTO, env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        prop.put(USE_SECOND_LEVEL_CACHE, env.getRequiredProperty("hibernate.cache.use_second_level_cache"));
        prop.put(USE_QUERY_CACHE, "false");
        //prop.put(DEFAULT_SCHEMA, dbSchema);
        prop.put(USE_REFLECTION_OPTIMIZER, "false");
        prop.put(DEFAULT_BATCH_FETCH_SIZE, "30");
        return prop;
    }

    @Bean(name="txManager")
    @Autowired
    public HibernateTransactionManager transactionManager( SessionFactory factory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(factory);
        return txManager;
    }


}
