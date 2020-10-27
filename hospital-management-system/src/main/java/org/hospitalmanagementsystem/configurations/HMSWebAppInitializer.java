package org.hospitalmanagementsystem.configurations;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
/**
 * <h1>HMSWebAppInitializer</h1>
 * This class is used to initialize the configuration for HMS web application.
 * @author s.sivasan
 * @since 10/23/2020
 * @version 1.0
 *
 */
public class HMSWebAppInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext appContext  = new AnnotationConfigWebApplicationContext();
		appContext.register(HMSDispatcherConfig.class);
		appContext.register(HMSRootConfigurer.class);
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",new DispatcherServlet(appContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		dispatcher.addMapping("*.ppl");
		dispatcher.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	}

}
