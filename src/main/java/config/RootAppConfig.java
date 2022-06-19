package config;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration   							 //  =  <context:annotation-config/>  <!-- 支援annotation效用 -->
@ComponentScan(basePackages = "backend")  // = <context:component-scan base-package="tw.leonchen"/> <!-- 去這個路徑掃描 -->
@EnableWebMvc 								 //<mvc:annotation-driven/>  <!-- 支援mvc annotation效用 -->
@EnableTransactionManagement  
@PropertySource("classpath:database.properties")
//基本連接註冊sql password url
											//<bean id="transactionManager" class="org.springframework.orm.hibernate5.HibernateTransactionManager">  <!-- 交易管理員 自動交易 -->
											//	<property name="sessionFactory" ref="sessionFactory"/>
											//</bean>
public class RootAppConfig {
	
	@Bean
	public DataSource dataSource() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean jndiBean = new JndiObjectFactoryBean();
		jndiBean.setJndiName("java:comp/env/connectSqlServerJdbc/SystemService");
		jndiBean.afterPropertiesSet();  //順序不可以移動 上面需先載入
		DataSource ds = (DataSource)jndiBean.getObject();
		return ds;
	}
//	   <bean id="dataSource" class="org.springframework.jndi.JndiObjectFactoryBean">
//       <property name="jndiName" value="java:comp/env/connectSqlServerJdbc/SystemService"/>
//       </bean>
	@Bean
	public LocalSessionFactoryBean sessionFactory() throws IllegalArgumentException, NamingException {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan( new String[]{"backend.hotel.model"});
		factoryBean.setHibernateProperties(addtionalProperties());
		return factoryBean;
	}
//	   <bean id="sessionFactory" class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
//       <property name="dataSource" ref="dataSource"/>
//       <property name="packagesToScan" value="tw.leonchen.model"/>
//       <property name="hibernateProperties">

		private Properties addtionalProperties() {
		Properties props = new Properties();
		props.put("hibernate.dialect", org.hibernate.dialect.SQLServerDialect.class);
		props.put("hibernate.show_sql", Boolean.TRUE);
		props.put("hibernate.format_sql", Boolean.TRUE);
//		props.put("hibernate.hbm2ddl.auto","update");  自動生成資料表
		//props.put("hibernate.current_session_context_class", "thread");
		return props;
		
//		    <prop key="hibernate.dialect">org.hibernate.dialect.SQLServerDialect</prop>
//          <prop key="hibernate.show_sql">true</prop>
//          <prop key="hibernate.format_sql">true</prop>
//<!--      <prop key="hibernate.current_session_context_class">thread</prop>    會跟spring mvc衝禿 所以註解-->   
	}
		@Bean
		@Autowired
		public HibernateTransactionManager  transactionManager(SessionFactory sessionFactory) {
			HibernateTransactionManager txMgr = new HibernateTransactionManager();
			txMgr.setSessionFactory(sessionFactory);
			return txMgr;
		}

}
