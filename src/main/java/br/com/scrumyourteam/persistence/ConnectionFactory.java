package br.com.scrumyourteam.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author marcella
 * Date: 03/02/2017
 * Objective: Create a JDBC Connection with a MySQL Database
 */

public class ConnectionFactory {
    private final String url = "jdbc:mysql://localhost/ScrumYourTeam";
    private final String user = "root";
    private final String password = "root";
    private final String timeZoneParam = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    
    public Connection getConnection()
    {
    
        try {
            //this command register the driver, without it, it doesn't work
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            return (Connection) DriverManager.getConnection(url+timeZoneParam, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get a new Connection: " + e);
        }
        
    }
}
