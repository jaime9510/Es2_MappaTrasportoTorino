package it.polito.ai.es2.orm.conf;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import it.polito.ai.es2.orm.mapping.BusLine;
import it.polito.ai.es2.orm.mapping.BusLineStop;
import it.polito.ai.es2.orm.mapping.BusStop;

public class HibernateUtil {
	
	
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

				Map<String, String> settings = new HashMap<String, String>();
				settings.put("hibernate.connection.driver_class", "org.postgresql.Driver");
				settings.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/trasporti");
				settings.put("hibernate.connection.username", "postgres");
				settings.put("hibernate.connection.password", "ai-user-password");
				settings.put("hibernate.show_sql", "true");
				settings.put("hibernate.hbm2ddl.auto", "validate");

				registryBuilder.applySettings(settings);

				registry = registryBuilder.build();

				MetadataSources sources = new MetadataSources(registry)
						.addAnnotatedClass(BusLine.class)
						.addAnnotatedClass(BusStop.class)
						.addAnnotatedClass(BusLineStop.class);

				Metadata metadata = sources.getMetadataBuilder().build();

				sessionFactory = metadata.getSessionFactoryBuilder().build();
			} catch (Exception e) {
				System.out.println("SessionFactory creation failed");
				e.printStackTrace();
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
			}
		}
		return sessionFactory;
	}
	
	public static void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	
}
