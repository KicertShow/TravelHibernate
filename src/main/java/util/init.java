package util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
@WebListener
public class init implements ServletContextListener {
	SessionFactory factory;
	public init() {
		System.out.println("初始化啟動 ~~~~");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("系統執行中ya");
		 factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("系統啟關閉了 sad");
		factory.close();
	}

}
