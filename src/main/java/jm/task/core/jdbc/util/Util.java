package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        properties.setProperty("hibernate.connection.url", url);
        properties.setProperty("hibernate.connection.username", login);
        properties.setProperty("hibernate.connection.password", password);
        properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        Configuration cfg = new Configuration();
        cfg.addProperties(properties);
        cfg.addAnnotatedClass(Util.class);
        return cfg.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, login, password);
    }


}
