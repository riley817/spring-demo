package io.tlor.spring.common.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DataBaseConfig {

    @Primary
    @Bean(name = "demoDataSource")
    @ConfigurationProperties(prefix = "datasource.demo")
    public DataSource demoDataSource() {
        return new HikariDataSource();
    }

    @Primary
    @Bean(name = "demoEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean demoEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(demoDataSource());
        em.setPackagesToScan(new String[] { "io.tlor.spring.common.model" });
        em.setPersistenceUnitName("demoPersistenceUnit");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Primary
    @Bean(name = "demoTransactionManager")
    public PlatformTransactionManager demoTransactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    public Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect"); // 방언
        properties.setProperty("hibernate.show_sql", "true");                           // SQL 보기
        properties.setProperty("hibernate.format_sql", "true");                         // SQL 정렬해서 보기
        properties.setProperty("hibernate.use_sql_comments", "true");                   // SQL 코멘트 보기
        properties.setProperty("hibernate.id.new_generator_mappings", "true");          // 새 버전의 ID 생성 옵션
        properties.setProperty("hibernate.hbm2ddl.auto", "create");                     // DDL 자동 생성 옵션
        return properties;
    }
}