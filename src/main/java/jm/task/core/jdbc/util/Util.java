package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/userdb?useSSL=false";
    private final static String LOGIN = "root";
    private final static String PASSWORD = "123321";

    public static SessionFactory getSessionFactory() {
        Configuration cfg = new Configuration();
        cfg.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        cfg.setProperty("hibernate.connection.url", URL);
        cfg.setProperty("hibernate.connection.username", LOGIN);
        cfg.setProperty("hibernate.connection.password", PASSWORD);
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        cfg.addAnnotatedClass(User.class);
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        return cfg.buildSessionFactory(serviceRegistry);
    }

}
