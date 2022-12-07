//package com.example.replication.config;
//
//
//import com.example.replication.enums.DBTypeEnum;
//import com.zaxxer.hikari.HikariDataSource;
//import org.hibernate.jpa.HibernatePersistenceProvider;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Properties;
//
//
///**
// * 关于数据源配置，参考SpringBoot官方文档第79章《Data Access》
// * 79. Data Access
// * 79.1 Configure a Custom DataSource
// * 79.2 Configure Two DataSources
// */
////@EnableTransactionManagement
//
//@Configuration
//public class DataSourceConfig {
//
//    public final  String MODEL_PACKAGE = "com.example.replication.entity";
//
//
//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
//        return new PersistenceExceptionTranslationPostProcessor();
//    }
////    @Bean
////    @ConfigurationProperties("spring.datasource.master")
////    public HikariDataSource masterDataSource() {
////        return DataSourceBuilder.create().type(HikariDataSource.class).build();
////    }
//
//    @Bean
//    @ConfigurationProperties("spring.datasource.slave1")
//    public HikariDataSource slave1DataSource() {
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }
//
//    @Bean
//    @ConfigurationProperties("spring.datasource.slave2")
//    public HikariDataSource slave2DataSource() {
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryWrite(
//            final HikariDataSource masterDataSource) {
//
//        return new LocalContainerEntityManagerFactoryBean() {{
//            Properties JPA_WRITE_PROPERTIES = new Properties() {{
//                put("hibernate.jdbc.batch_size", "50");
//                put("hibernate.order_updates", "true");
//                put("hibernate.batch_versioned_data", "true");
//                put("hibernate.order_inserts", "true");
//                put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
//                put("hibernate.hbm2ddl.auto", "update");
//                put("hibernate.ddl-auto", "update");
//            }};
//            JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//            setJpaVendorAdapter(vendorAdapter);
//            setDataSource(masterDataSource);
//            setPersistenceProviderClass(HibernatePersistenceProvider.class);
//            setPersistenceUnitName("write");
//            setPackagesToScan(MODEL_PACKAGE);
//            setJpaProperties(JPA_WRITE_PROPERTIES);
//        }};
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactorySlave1(
//            @Qualifier("slave1DataSource") DataSource slave1DataSource) {
//
//        return new LocalContainerEntityManagerFactoryBean() {{
//            Properties JPA_READ_PROPERTIES = new Properties() {{
//                put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
//                put("hibernate.hbm2ddl.auto", "update");
//                put("hibernate.ddl-auto", "update");
//                put("show-sql", "true");
//            }};
//            JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//            setJpaVendorAdapter(vendorAdapter);
//            setDataSource(slave1DataSource);
//            setPersistenceProviderClass(HibernatePersistenceProvider.class);
//            setPersistenceUnitName("read");
//            setPackagesToScan(MODEL_PACKAGE);
//            setJpaProperties(JPA_READ_PROPERTIES);
//        }};
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactorySlave2(
//            @Qualifier("slave2DataSource") DataSource slave2DataSource) {
//
//        return new LocalContainerEntityManagerFactoryBean() {{
//            Properties JPA_READ_PROPERTIES = new Properties() {{
//                put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
//                put("hibernate.hbm2ddl.auto", "update");
//                put("hibernate.ddl-auto", "update");
//                put("show-sql", "true");
//            }};
//            JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//            setJpaVendorAdapter(vendorAdapter);
//            setDataSource(slave2DataSource);
//            setPersistenceProviderClass(HibernatePersistenceProvider.class);
//            setPersistenceUnitName("read");
//            setPackagesToScan(MODEL_PACKAGE);
//            setJpaProperties(JPA_READ_PROPERTIES);
//        }};
//    }
//
//
//    @Bean
//    public DataSource myRoutingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
//                                          @Qualifier("slave1DataSource") DataSource slave1DataSource,
//                                          @Qualifier("slave2DataSource") DataSource slave2DataSource) {
//        Map<Object, Object> targetDataSources = new HashMap<>();
//        targetDataSources.put(DBTypeEnum.MASTER, masterDataSource);
//        targetDataSources.put(DBTypeEnum.SLAVE1, slave1DataSource);
//        targetDataSources.put(DBTypeEnum.SLAVE2, slave2DataSource);
//        MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
//        myRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
//        myRoutingDataSource.setTargetDataSources(targetDataSources);
//        return myRoutingDataSource;
//    }
//
//    @Bean
//    PlatformTransactionManager customerSlave1TransactionManager() {
//        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactorySlave2(slave2DataSource()).getObject()));
//    }
//    @Bean
//    PlatformTransactionManager customerSlave2TransactionManager() {
//        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactorySlave1(slave1DataSource()).getObject()));
//    }
//    @Bean
//    PlatformTransactionManager customerMasterTransactionManager() {
//        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactoryWrite(masterDataSource()).getObject()));
//    }
//
//}
