package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.configurations;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;

/**
 * Database configuration class
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@PropertySource(value = "classpath:application.properties")
@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {
    @Bean
    public DataSource dataSource(HikariConfig config) {
        return new HikariDataSource(config);
    }

    @Bean
    public HikariConfig hikariConfig(@Value("${db.url}") String url,
                                     @Value("${db.user}") String username,
                                     @Value("${db.password}") String password,
                                     @Value("${db.driver}") String driverName,
                                     @Value("${db.connectionNumber}") int connectionNumber) {
        HikariConfig config = new HikariConfig();
        config.setUsername(username);
        config.setPassword(password);
        config.setJdbcUrl(url);
        config.setDriverClassName(driverName);
        config.setMaximumPoolSize(connectionNumber);
        return config;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
