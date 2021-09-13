package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() {

        final String URL = "jdbc:mysql://localhost:3306/first_lesson?useSSL=false";
        final String USERNAME = "root";
        final String PASSWORD = "Silmaril66";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return conn;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
}
