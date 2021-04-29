package com.sandbox.webcustomertracker;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement	
public class AppConfig {

	@SuppressWarnings("finally")
	@Bean
	public ComboPooledDataSource myDataSource() {
		ComboPooledDataSource dataSource = null;
		try {
			dataSource = new ComboPooledDataSource();
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
			dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC");
			dataSource.setUser("springstudent");
			dataSource.setPassword("springstudent");
			dataSource.setMinPoolSize(5);
			dataSource.setMaxPoolSize(20);
			dataSource.setMaxIdleTime(30000);
		} 
		catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		finally {
			return dataSource;
		}
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean theBean = new LocalSessionFactoryBean();
		Properties hibernateProps = new Properties();
		hibernateProps.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		hibernateProps.setProperty("hibernate.show_sql", "true");
		theBean.setDataSource(myDataSource());
		theBean.setPackagesToScan("com.sandbox.webcustomertracker.entity");
		theBean.setHibernateProperties(hibernateProps);
		return theBean;
	}
	
	@Bean
	public HibernateTransactionManager myTransactionManager() throws IOException {
		HibernateTransactionManager theManager = new HibernateTransactionManager();
		SessionFactory sf = sessionFactory().getObject(); 	
		theManager.setSessionFactory(sf);
		return theManager;
	}
	
}
