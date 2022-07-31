package com.hanwha.tax.apiserver.config;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Properties;

@Configuration
@EnableJpaRepositories (
        basePackages = "com.hanwha.tax.apiserver.repository",
        entityManagerFactoryRef = "primaryEntityManager",
        transactionManagerRef = "primaryTransactionManager"
)
public class PrimaryDsConfig {
    @Autowired
    private Environment env;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean primaryEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(primaryDataSource());

        //Entity 패키지 경로
        em.setPackagesToScan("com.hanwha.tax.apiserver.entity");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        //Hibernate 설정
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.ddl-auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.hibernate.dialect"));
        properties.put("hibernate.format_sql", env.getProperty("spring.jpa.hibernate.format_sql"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Primary
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager primaryTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(primaryEntityManager().getObject());
        return transactionManager;
    }
/*
    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSourceProperties AdbDsProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource AdbDataSource(DataSourceProperties properties) {
        //DataSourceBuilder.create().build();
        return properties.initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean primaryEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        HibernateJpaVendorAdapter hbAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(hbAdapter);

        em.setDataSource(AdbDataSource(AdbDsProperties()));
        em.setPackagesToScan("com.hanwha.tax.apiserver.entity"); // database entity package path
        em.setJpaProperties(jpaProperties());

        return em;
    }

    @Bean
    @Primary
    public PlatformTransactionManager primaryTransactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();

        final String rootPath = "spring.jpa.hibernate";

//        properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty(rootPath + ".hbm2ddl.auto"));
        properties.setProperty("hibernate.dialect", env.getProperty(rootPath + ".dialect"));
//        properties.setProperty("hibernate.show_sql", env.getProperty(rootPath + ".show_sql"));
        properties.setProperty("hibernate.format_sql", env.getProperty(rootPath + ".format_sql"));
//        properties.setProperty("hibernate.enable_lazy_load_no_trans",
//                env.getProperty(rootPath + ".enable_lazy_load_no_trans"));

        return properties;
    }*/
}
