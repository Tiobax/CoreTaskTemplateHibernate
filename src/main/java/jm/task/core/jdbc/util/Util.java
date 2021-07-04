package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final static String url = "jdbc:mysql://localhost:3306/userdb?useSSL=false";
    private final static String login = "root";
    private final static String password = "123321";
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        Configuration cfg = new Configuration();
        cfg.addResource("User.hbm.xml");
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        cfg.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        cfg.setProperty("hibernate.connection.url", url);
        cfg.setProperty("hibernate.connection.username", login);
        cfg.setProperty("hibernate.connection.password", password);

        return cfg.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, login, password);
    }


}
