/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Student;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gottl
 */
public class DB_Tester {
    private Connection connection;
    public DB_Tester() throws ClassNotFoundException{
       
        Class.forName("org.postgresql.Driver");
        
    }
    public void connect(String dbName) throws SQLException{
        String url = "jdbc:postgresql://localhost:5432/"+dbName;
        String username = "postgres";
        String password = "postgres";
        
        connection = DriverManager.getConnection(url,username,password);
        
    }
    public void disconnect() throws SQLException{
        if(connection != null)
            connection.close();
    }
    public void createTable() throws SQLException{
        String query = "CREATE TABLE student("
                + "student_id SERIAL PRIMARY KEY,"
                + "catalog_nr INTEGER NOT NULL,"
                + "firstname VARCHAR NOT NULL,"
                + "lastname VARCHAR NOT NULL,"
                + "birthdate DATE NOT NULL)";
        Statement statement = connection.createStatement();
        statement.execute(query);
    }
    public void createDatabase(String dbName) throws SQLException{
        String query = "CREATE DATABASE " + dbName.toLowerCase();
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
        
    }
    public List<Student> getTableContent() throws SQLException{
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM student";
        ResultSet rs = statement.executeQuery(query);
        List<Student> students = new ArrayList<>();
        
        while(rs.next()){
            students.add(new Student(
            rs.getInt("student_id"),
            rs.getInt("catalog_nr"),
            rs.getString("firstname"),
            rs.getString("lastname"),
            rs.getDate("birthdate").toLocalDate()));
        }
        statement.close();
        return students;
    }
    public void insertStudent(Student student) throws SQLException{
        Statement statement = connection.createStatement();
        Date birthdate = Date.valueOf(student.getBirthdate());
        String query = String.format("INSERT INTO student(catalog_nr, firstname, lastname, birthdate)"
                                   + "VALUES(%d, '%s', '%s', '%s');", student.getCatalog_nr(),student.getFirstname(),
                                                                                 student.getLastname(), birthdate);
        statement.executeUpdate(query);
        statement.close();
    }
    public static void main(String[] args) {
        try {
            
            DB_Tester dbt = new DB_Tester();
            dbt.connect("studentdb");
//            try{
//            dbt.createDatabase("studentdb");
//            }catch(SQLException ex){
//                System.out.println("Database already exists");
//            }
//            try{
//                dbt.createTable();
//            }catch(SQLException ex){
//                System.out.println("Table already exists");
//            }
//            dbt.insertStudent(new Student(0,1,"Leon", "Anghel", LocalDate.of(2002, 10, 24)));
            dbt.getTableContent().stream()
                    .forEach(System.out::println);
            dbt.disconnect();
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }
}
