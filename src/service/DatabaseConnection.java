package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection = null;

    public static final String JDBC_DRIVER = "jdbc:mysql://localhost:3306/racing";
    public static final String JDBC_PWD = "andreibogos123";
    public static final String JDBC_USER = "root";

    public static Connection getConnection() throws SQLException {
        if(connection == null){
            connection = DriverManager.getConnection(JDBC_DRIVER, JDBC_USER, JDBC_PWD);
        }
        return connection;
    }
}