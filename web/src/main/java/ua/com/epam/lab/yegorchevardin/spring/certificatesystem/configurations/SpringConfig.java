package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC configuration class
 * @author Yehor Chevardin
 * @version 1.0.0
 */
@EnableWebMvc
@Configuration
@ComponentScan("ua.com.epam.lab.yegorchevardin.spring.certificatesystem")
public class SpringConfig implements WebMvcConfigurer {
}
