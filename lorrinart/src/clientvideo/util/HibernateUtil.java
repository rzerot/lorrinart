package clientvideo.util;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	private static SessionFactory createSessionFactory() {
		System.out.println(new File(".").getAbsolutePath());
		try {
			if (sessionFactory == null) {
				Configuration configuration = new Configuration()
						.configure("hibernate.cfg.xml");

				sessionFactory = configuration.buildSessionFactory();
			}
			return sessionFactory;
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {

		return createSessionFactory();
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

}
