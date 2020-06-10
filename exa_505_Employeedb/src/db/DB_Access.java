/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import beans.Employee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gottl
 */
public class DB_Access {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static DB_Access dbInstance = null; 
    private DB_Database db;
    private PreparedStatement getAllEmployees;
    public static DB_Access getInstance() {
        if (dbInstance == null) {
            dbInstance = new DB_Access();
        }
        return dbInstance;
    }

    private DB_Access() {
        try {
            db = new DB_Database();
            db.connect();

        } catch (ClassNotFoundException ex) {
            System.out.println("An error occured while connecting to the database");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("An error occured while performing SQL operations");
            ex.printStackTrace();
        }
    }
    
    public List<Employee> getEmployees() throws SQLException{
        List<Employee> employees = new ArrayList<>();
        if(getAllEmployees == null){
            getAllEmployees = db.getConnection().prepareStatement("SELECT * FROM employees");
        }
        ResultSet results = getAllEmployees.executeQuery();
        while(results.next()){
            employees.add(new Employee(
                    results.getString("first_name"),
                    results.getString("lastname"),
                    results.getString("gender").charAt(0),
                    results.getDate("birth_date").toLocalDate(),
                    results.getDate("hire_date").toLocalDate()
            ));
        }
        return employees;
    }
    
    public List<Employee> getEmployeesFiltered(char gender, LocalDate birthdate, String deptName) throws SQLException{
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT e.* FROM employees e";
        if(deptName != null){
            query += "INNER JOIN dept_emp de ON e.emp_no = de.emp_no " +
                     "INNER JOIN departments d ON de.dept_no = d.dept_no "+
                     "WHERE d.dept_name = " + deptName;
        }
        if(true){
            if(query.contains("WHERE")) /*-->*/ query += " AND ";
            else /*-->*/ query += "WHERE ";
            query += "e.gender = " + gender;
        }
        if(birthdate != null){
            if(query.contains("WHERE")) /*-->*/ query += " AND ";
            else /*-->*/ query += "WHERE ";
            java.sql.Date dateOfBirth = java.sql.Date.valueOf(birthdate);
            query += "birth_date = " + dateOfBirth;
        }
        query += ";";
        Statement statement = db.getStatement();
        ResultSet results = statement.executeQuery(query);
        while(results.next()){
            employees.add(new Employee(
                    results.getString("first_name"),
                    results.getString("lastname"),
                    results.getString("gender").charAt(0),
                    results.getDate("birth_date").toLocalDate(),
                    results.getDate("hire_date").toLocalDate()
            ));
        }
        db.releaseStatement(statement);
        return employees;
    }
}
