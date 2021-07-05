package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.MetadataSource;
import org.hibernate.metamodel.Metadata;
import org.hibernate.metamodel.MetadataSources;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private final static String url = "jdbc:mysql://localhost:3306/userdb?useSSL=false";
    private final static String login = "root";
    private final static String password = "123321";
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        Configuration cfg = new Configuration();
        cfg.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        cfg.setProperty("hibernate.connection.url", url);
        cfg.setProperty("hibernate.connection.username", login);
        cfg.setProperty("hibernate.connection.password", password);
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        cfg.addAnnotatedClass(User.class);
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
//        MetadataSources metadataSource = new MetadataSources(serviceRegistry);
//        Metadata metadata = metadataSource.getMetadataBuilder().build();
//        return metadata.getSessionFactoryBuilder().build();
        return cfg.buildSessionFactory(serviceRegistry);
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void closeSession(Session session) {
        session.close();
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, login, password);
    }


}
