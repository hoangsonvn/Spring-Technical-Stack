package com.example.replication.config;

import com.example.replication.enums.DBTypeEnum;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

@Configuration
@PropertySources({@PropertySource("classpath:application.properties")})
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.replication.repository",
        entityManagerFactoryRef = "multiEntityManager",
        transactionManagerRef = "multiTransactionManager"
)
public class MutiDatabaseConfig {
    public final String MODEL_PACKAGE = "com.example.replication.entity";

    @Autowired
    private Environment env;
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean em
//                = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSourceMaster1());
//        em.setPackagesToScan(MODEL_PACKAGE);
//
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        em.setJpaProperties(additionalProperties());
//
//        return em;
//    }


    @Bean(name = "multiEntityManager")
    public LocalContainerEntityManagerFactoryBean multiEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(multiRoutingDataSource(dataSourceMaster1(),dataSourceSlave1(),dataSourceSlave2()));
        em.setPackagesToScan(MODEL_PACKAGE);
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySlave1() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSourceSlave1());
        em.setPackagesToScan(MODEL_PACKAGE);

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySlave2() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSourceSlave2());
        em.setPackagesToScan(MODEL_PACKAGE);

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }


//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name.master1"));
//        dataSource.setUrl(env.getProperty("spring.datasource.url.master1"));
//        dataSource.setUsername(env.getProperty("spring.datasource.username.master1"));
//        dataSource.setPassword(env.getProperty("spring.datasource.password.master1"));
//        return dataSource;
//    }

    private void setConfigHikari(HikariConfig config) {
        config.setIdleTimeout(Long.parseLong(Objects.requireNonNull(env.getProperty("spring.datasource.hikari.idleTimeout"))));
        config.setConnectionTimeout(Long.parseLong(Objects.requireNonNull(env.getProperty("spring.datasource.hikari.connectionTimeout"))));
        config.setMinimumIdle(Integer.parseInt(Objects.requireNonNull(env.getProperty("spring.datasource.hikari.minimumIdle"))));
        config.setMaximumPoolSize(Integer.parseInt(Objects.requireNonNull(env.getProperty("spring.datasource.hikari.maximumPoolSize"))));
    }

    @Bean
    public HikariDataSource dataSourceMaster1() {
        HikariConfig hikariConfig = new HikariConfig();
        setConfigHikari(hikariConfig);
        hikariConfig.setPoolName(env.getProperty("hikari.poolname.master1"));
        hikariConfig.setDriverClassName(env.getProperty("spring.datasource.driver-class-name.master1"));
        hikariConfig.setJdbcUrl(env.getProperty("spring.datasource.url.master1"));
        hikariConfig.setUsername(env.getProperty("spring.datasource.username.master1"));
        hikariConfig.setPassword(env.getProperty("spring.datasource.password.master1"));
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public HikariDataSource dataSourceSlave1() {
        HikariConfig hikariConfig = new HikariConfig();
        setConfigHikari(hikariConfig);
        hikariConfig.setPoolName(env.getProperty("hikari.poolname.slave1"));
        hikariConfig.setDriverClassName(env.getProperty("spring.datasource.driver-class-name.slave1"));
        hikariConfig.setJdbcUrl(env.getProperty("spring.datasource.url.slave1"));
        hikariConfig.setUsername(env.getProperty("spring.datasource.username.slave1"));
        hikariConfig.setPassword(env.getProperty("spring.datasource.password.slave1"));
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public HikariDataSource dataSourceSlave2() {
        HikariConfig hikariConfig = new HikariConfig();
        setConfigHikari(hikariConfig);
        hikariConfig.setPoolName(env.getProperty("hikari.poolname.slave2"));
        hikariConfig.setDriverClassName(env.getProperty("spring.datasource.driver-class-name.slave2"));
        hikariConfig.setJdbcUrl(env.getProperty("spring.datasource.url.slave2"));
        hikariConfig.setUsername(env.getProperty("spring.datasource.username.slave2"));
        hikariConfig.setPassword(env.getProperty("spring.datasource.password.slave2"));
        return new HikariDataSource(hikariConfig);
    }


//    @Bean
//    public PlatformTransactionManager transactionManagerMaster1() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//
//        return transactionManager;
//    }

    @Bean(name = "multiTransactionManager")
    public PlatformTransactionManager multiTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(multiEntityManager().getObject());
        return transactionManager;
    }


//    @Bean
//    public PlatformTransactionManager transactionManagerSlave1() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactorySlave1().getObject());
//
//        return transactionManager;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManagerSlave2() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactorySlave2().getObject());
//
//        return transactionManager;
//    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        Properties properties = new Properties() {{
            put("hibernate.jdbc.batch_size", "1000");
            put("hibernate.order_updates", "true");
            put("hibernate.batch_versioned_data", "true");
            put("hibernate.order_inserts", "true");
            put("hibernate.show_sql", "true");
            put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
            put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto.master1.common"));
        }};

        return properties;
    }


    @Bean(name = "multiRoutingDataSource")
    public DataSource multiRoutingDataSource(@Qualifier("dataSourceMaster1") DataSource masterDataSource,
                                             @Qualifier("dataSourceSlave1") DataSource slave1DataSource,
                                             @Qualifier("dataSourceSlave2") DataSource slave2DataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBTypeEnum.MASTER, masterDataSource);
        targetDataSources.put(DBTypeEnum.SLAVE1, slave1DataSource);
        targetDataSources.put(DBTypeEnum.SLAVE2, slave2DataSource);
        MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
        myRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
        myRoutingDataSource.setTargetDataSources(targetDataSources);
        return myRoutingDataSource;
    }
}
