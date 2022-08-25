package com.ideas2it.confiq;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionConfiq {


    private static Connection connection = null;
    private static String URL = "jdbc:mysql://localhost/employeedetails1";
    private static String userName = "root";
    private static String passWord = "gowtham@123";
    private static ConnectionConfiq connectionConfiq;

    private ConnectionConfiq () {
    
        try {
            connection = DriverManager.getConnection(URL, userName, passWord);
        } catch (SQLException e) {
           System.out.println(e);
        }
        
    }
    public static Connection getInstance() throws SQLException {
        if(connection == null || connection.isClosed() ) {
            connectionConfiq = new ConnectionConfiq();
        }
        return connection;

    }
}  



