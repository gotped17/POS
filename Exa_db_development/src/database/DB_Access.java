/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Student;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gottl
 */
public class DB_Access {
    private static DB_Access theInstance = null;
    private DB_Database db;
    
    public static DB_Access getInstance() {
        if(theInstance == null){
            theInstance = new DB_Access();
        }
        return theInstance;
    }
    
    private DB_Access() {
        try {
            db = new DB_Database();
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Database problem - possible cause: Driver not found");
        }catch(SQLException ex){
            throw new RuntimeException("Database problem - possible cause: " + ex.toString());
        }
    }
    
    public List<Student> getAllStudents(String className) throws SQLException{
        String query = "SELECT * FROM student WHERE CLASS = '"+className+"';";
        Statement stat = db.getStatement();
        ResultSet rs = stat.executeQuery(query);
        List<Student> students = new ArrayList<>();
        while(rs.next()){
            students.add(new Student());
        }
        db.releaseStatement(stat);
        return students;
    }
    public void insertStudent(Student student){
        String query = "";
    }
    
}
