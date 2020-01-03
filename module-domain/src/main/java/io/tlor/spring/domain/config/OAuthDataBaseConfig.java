package io.tlor.spring.domain.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
@ComponentScan(basePackages = {"io.tlor.spring.domain.model.oauth"})
public class OAuthDataBaseConfig {

    @Primary
    @Bean(name = "oauthDataSource")
    @ConfigurationProperties(prefix = "datasource.oauth")
    public DataSource oauthDataSource() {
        return new HikariDataSource();
    }

    @Primary
    @Bean(name = "oauthEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean oauthEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(oauthDataSource());
        em.setPackagesToScan(new String[] { "io.tlor.spring.domain.model.oauth" });
        em.setPersistenceUnitName("oauthPersistenceUnit");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Primary
    @Bean(name = "oauthTransactionManager")
    public PlatformTransactionManager oauthTransactionManager(EntityManagerFactory emf) {
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
        // properties.setProperty("hibernate.hbm2ddl.auto", "create");                     // DDL 자동 생성 옵션
        properties.setProperty("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
        return properties;
    }
}
