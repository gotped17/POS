/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gottl
 */
public class DB_Database {
    private String dbUrl;
    private String dbDriver;
    private String dbUsername;
    private String dbPassword;
    private Connection connection;
    private DB_CachedConnection cachedConnection;
    
    public DB_Database() throws ClassNotFoundException, SQLException {
        loadProperties();
        Class.forName(dbDriver);
        connect();
    }
    
    public void connect() throws SQLException{
        if(connection !=null){
            connection.close();
        }
        connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        cachedConnection = new DB_CachedConnection(connection);
    }
    
    public void disconnect() throws SQLException {
        if(connection != null){
         connection.close();
         cachedConnection = null;
        }
    }

    public Statement getStatement() throws SQLException{
        if(connection == null || cachedConnection == null){
            throw new RuntimeException("Database Connection error");
        }
        return cachedConnection.getStatement();
    }
    public void releaseStatement(Statement statement){
        if(connection == null || cachedConnection == null){
            throw new RuntimeException("Database Connection error");
        }
        cachedConnection.releaseStatement(statement);
    }
    
    public Connection getConnection() {
        return connection;
    }
            
    public void loadProperties() {
        dbUrl = DB_Properties.getPropertyValue("url");
        dbDriver = DB_Properties.getPropertyValue("driver");
        dbUsername = DB_Properties.getPropertyValue("username");
        dbPassword = DB_Properties.getPropertyValue("password");
        
    }
    
    
    public static void main(String[] args) {
        try {
            DB_Database db = new DB_Database();
            db.connect();
            db.disconnect();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
