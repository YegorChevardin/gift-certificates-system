package ua.com.epam.lab.yegorchevardin.spring.certificatesystem.configurations;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * DispatcherServlet configuration class
 * @author Yehor Chevardin
 * @version 1.0.0
 */
public class SpringDispatcher extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected DispatcherServlet createDispatcherServlet(WebApplicationContext applicationContext) {
        DispatcherServlet ds = new DispatcherServlet(applicationContext);
        ds.setThrowExceptionIfNoHandlerFound(true);
        return ds;
    }
}
