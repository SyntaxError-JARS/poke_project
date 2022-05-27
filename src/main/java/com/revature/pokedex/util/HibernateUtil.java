package com.revature.pokedex.util;

import com.revature.pokedex.ability.Ability;
import com.revature.pokedex.element_type.ElementType;
import com.revature.pokedex.pokemon.Pokemon;
import com.revature.pokedex.trainer.Trainer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static Session session;

    public static Session getSession() throws IOException {
        if(sessionFactory == null) {
            Configuration configuration = new Configuration();
//            Properties props = new Properties();
            // AZURE does not like Threads and trying to load resources via a ClassLoader
//            ClassLoader loader = Thread.currentThread().getContextClassLoader();
//            props.load(loader.getResourceAsStream("hibernate.properties"));
//            // Add properties to our configuration
//            configuration.setProperties(props);

            String url = System.getenv("SQLAZURECONNSTR_PokeProjectDB");
            String username = System.getenv("DBUSER");
            String password = System.getenv("DBPASS");

            configuration.setProperty("hibernate.connection.url", url);
            configuration.setProperty("hibernate.connection.username", username);
            configuration.setProperty("hibernate.connection.password", password);
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
            configuration.setProperty("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");

            // ONE ADDITIONAL STEP I NEED TO INCLUDE
            configuration.addAnnotatedClass(Trainer.class);
            configuration.addAnnotatedClass(Pokemon.class);
            configuration.addAnnotatedClass(ElementType.class);
            configuration.addAnnotatedClass(Ability.class);

            // ServiceRegistry
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        if(session == null) {
            session = sessionFactory.openSession();
        }

        return session;
    }

    public static void closeSession() {
        session.close();
        session = null;

    }
}
