package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/userdb?useSSL=false";
    private static final String login = "root";
    private static final String password = "123321";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, login, password);
    }
}
