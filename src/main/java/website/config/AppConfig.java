package website.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("website")
@PropertySource("classpath:env.properties")
public class AppConfig  implements WebMvcConfigurer {

	@Autowired
	private Environment env;
	
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	@Bean
	public DataSource dataSource() {
	 ComboPooledDataSource dataSource = new ComboPooledDataSource();

	 try {
	 dataSource.setDriverClass("com.mysql.jdbc.Driver");
	 }
	 catch (PropertyVetoException exc) {
	 throw new RuntimeException(exc);
	 }

	 dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
	 dataSource.setUser(env.getProperty("jdbc.user"));
	 dataSource.setPassword(env.getProperty("jdbc.password"));

	 dataSource.setInitialPoolSize(Integer.parseInt(
			 env.getProperty("connection.pool.initialPoolSize")));
	 dataSource.setMinPoolSize(Integer.parseInt(
			env.getProperty("connection.pool.minPoolSize")));
	 dataSource.setMaxPoolSize(Integer.parseInt(
			 env.getProperty("connection.pool.maxPoolSize")));
	 dataSource.setMaxIdleTime(Integer.parseInt(
			 env.getProperty("connection.pool.maxIdleTime")));
	 return dataSource;
	}
	
	private Properties getHibernateProperties() {

		Properties properties = new Properties();

		properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		
		
		return properties;				
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());
		
		return sessionFactory;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/resources/**")
          .addResourceLocations("/resources/"); 
    }
}
