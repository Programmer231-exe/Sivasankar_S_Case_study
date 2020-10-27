package org.hospitalmanagementsystem.configurations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = { "org.hospitalmanagementsystem" })
@EnableTransactionManagement
@PropertySource(value = { "classpath:/properties/DBConnection.properties",
		"classpath:/properties/Hibernate.properties" })
public class HMSRootConfigurer {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource getDataSource(Environment enviroment) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.Driver"));
		dataSource.setUrl(environment.getRequiredProperty("jdbc.URL"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		return dataSource;
	}

	public Properties getDataSourceProperties() {
		Properties properties = new Properties();
		try {
			properties.load(new InputStreamReader(new FileInputStream("DBConnection.properties")));
		} catch (FileNotFoundException fne) {
			System.out.println(fne.getMessage());
		} catch (IOException oe) {
			System.out.println(oe.getMessage());
		}
		return properties;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean getHMSDatabaseEntityManager() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(getDataSource(environment));
		entityManagerFactory.setPackagesToScan("org.hospitalmanagementsystem.entities");
		entityManagerFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactory.setJpaProperties(getHibernateProperties(environment));
		return entityManagerFactory;

	}
	
	@Bean
	@Qualifier(value = "entityManager")
	public EntityManager getEntityManager() {
		return  getHMSDatabaseEntityManager().getObject().createEntityManager();
	}
	
	public Properties getHibernateProperties(Environment environment) {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect",environment.getRequiredProperty("hibernate.dialect"));
		properties.setProperty("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		properties.setProperty("hibernate.show_sql",environment.getRequiredProperty("hibernate.show_sql"));
		properties.setProperty("hibernate.connection_autocommit", environment.getRequiredProperty("hibernate.connection_autocommit"));
		properties.setProperty("hibernate.use_sql_comments",environment.getRequiredProperty("hibernate.use_sql_comments"));
		properties.setProperty("hibernate.connection.pool_size",environment.getRequiredProperty("hibernate.connection.pool_size"));
		return properties;
	}

	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
}
