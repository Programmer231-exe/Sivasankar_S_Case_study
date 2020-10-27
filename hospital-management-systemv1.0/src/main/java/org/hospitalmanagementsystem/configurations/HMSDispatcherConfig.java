package org.hospitalmanagementsystem.configurations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "org.hospitalmanagementsystem" })
public class HMSDispatcherConfig implements WebMvcConfigurer {

	@Bean
	public ViewResolver getViewResolverForHMS() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

	@Bean
	public HandlerExceptionResolver handlerExceptionResolver() {
		HandlerExceptionResolver myResolver = new HandlerExceptionResolver() {

			@Override
			public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
					Object handler, Exception exception) {
				
				ModelAndView mav = new ModelAndView("error");
				mav.addObject("error", exception);
				return mav;
			}
		};
		return myResolver;
	}

}
