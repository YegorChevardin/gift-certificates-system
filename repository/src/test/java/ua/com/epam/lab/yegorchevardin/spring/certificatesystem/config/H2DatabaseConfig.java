package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * Configuration class for testing the database (h2)
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@Configuration
@ComponentScan("ua.com.epam.lab.yegorchevardin.spring.certificatesystem")
@PropertySource("classpath:database.properties")
@Profile("test")
public class H2DatabaseConfig {
    @Bean
    public DataSource dataSource(HikariConfig config) {
        HikariDataSource dataSource = new HikariDataSource(config);

        Resource initData = new ClassPathResource("CertificateSystemDump.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initData);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
        return dataSource;
    }

    @Bean
    public HikariConfig hikariConfig(@Value("${h2.url}") String url,
                                     @Value("${h2.user}") String username,
                                     @Value("${h2.password}") String password,
                                     @Value("${h2.driver}") String driverName,
                                     @Value("${h2.connectionNumber}") int connectionNumber) {
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
}
