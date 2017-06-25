
package com.mycompany.ProjectManagementSystem.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author YARUS
 */
public class ConnectionPool {    
    
    private static BasicDataSource  dataSource;
    
    static {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUsername("MAX");
        dataSource.setPassword("MAX");
        dataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:XE");               
        dataSource.setMinIdle(30);       
        dataSource.setMaxOpenPreparedStatements(500);
    }

    private ConnectionPool() {
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }
    
    public static void close(Connection connection) {        
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    
    public static void close(PreparedStatement preparedStatement) {        
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    
    public static void close(Statement statement) {        
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
        
    public static void close(ResultSet resultSet) {        
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
}
