package com.ideas2it.config;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionConfig {


    private static Connection connection = null;
    private static String URL = "jdbc:mysql://localhost/employeedetails1";
    private static String userName = "root";
    private static String passWord = "gowtham@123";
    private static ConnectionConfig connectionConfig;

    private ConnectionConfig () {
    
        try {
            connection = DriverManager.getConnection(URL, userName, passWord);
        } catch (SQLException e) {
           System.out.println(e);
        }
        
    }
    public static Connection getInstance() throws SQLException {
        if(connection == null || connection.isClosed() ) {
            connectionConfig = new ConnectionConfig();
        }
        return connection;

    }
}  



