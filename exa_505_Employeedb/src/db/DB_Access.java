/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import beans.Department;
import beans.Employee;
import beans.Gender;
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
    private String oldDept = "";
    private int offset = 0;
    private DB_Database db;
    private PreparedStatement getAllEmployees;
    private PreparedStatement getAllDepts;
    private String recentEmployeeQuery = "";
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
                    results.getString("last_name"),
                    results.getString("gender").toLowerCase().equals("M") ? Gender.M : Gender.F,
                    results.getDate("birth_date").toLocalDate(),
                    results.getDate("hire_date").toLocalDate()
            ));
        }
        recentEmployeeQuery = "SELECT * FROM employees";
        return employees;
    }
    
    public List<Employee> getEmployeesFiltered(Gender gender, LocalDate birthdate, String deptName) throws SQLException{
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT e.* FROM employees e INNER JOIN dept_emp de ON e.emp_no = de.emp_no INNER JOIN departments d ON de.dept_no = d.dept_no WHERE d.dept_name = ";
        if(deptName != null){
            oldDept = deptName;
        }
        else{
            deptName = oldDept;
        }
        query += "'"+deptName+"'";
        if(gender != null){
            query += " AND ";
            query += "e.gender = " + gender;
        }
        if(birthdate != null){
            query += " AND ";
            java.sql.Date dateOfBirth = java.sql.Date.valueOf(birthdate);
            query += "birth_date = " + dateOfBirth;
        }
        recentEmployeeQuery = query;
        query += "\n LIMIT 100 OFFSET " + offset+";";
        Statement statement = db.getStatement();
        ResultSet results = statement.executeQuery(query);
        
        while(results.next()){
            employees.add(new Employee(
                    results.getString("first_name"),
                    results.getString("last_name"),
                    results.getString("gender").toLowerCase().equals("M") ? Gender.M : Gender.F,
                    results.getDate("birth_date").toLocalDate(),
                    results.getDate("hire_date").toLocalDate()
            ));
        }
        // System.out.println(query);
        db.releaseStatement(statement);
        
        return employees;
    }
        //uses the last used sql query to take over the filters used the last time
    public List<Employee> getEmployeesFiltered() throws SQLException{
        List<Employee> employees = new ArrayList<>();
        Statement statement = db.getStatement();
        String query = recentEmployeeQuery + "\n LIMIT 100 OFFSET " + offset+";";
        ResultSet results = statement.executeQuery(query);
        while(results.next()){
            employees.add(new Employee(
                    results.getString("first_name"),
                    results.getString("last_name"),
                    results.getString("gender").toLowerCase().equals("M") ? Gender.M : Gender.F,
                    results.getDate("birth_date").toLocalDate(),
                    results.getDate("hire_date").toLocalDate()
            ));
        }
        
        db.releaseStatement(statement);
        return employees;
    }
    //returns an sorted list of employees
        //args: index 0 => column after which the data should be sorted
        //      index 1 => Determining if data should be ascending or descending
    public List<Employee> getSortedEmployees(String[] args) throws SQLException{
        recentEmployeeQuery = recentEmployeeQuery.replace(";", "");
        recentEmployeeQuery = String.format("%s ORDER BY %s %s", recentEmployeeQuery, args[0], args[1]);
        String query = recentEmployeeQuery + "\n LIMIT 100 OFFSET " + offset+";";
        Statement statement = db.getStatement();
        ResultSet results = statement.executeQuery(recentEmployeeQuery);
        List<Employee> employees = new ArrayList<>();
        while(results.next()){
            employees.add(new Employee(
                    results.getString("first_name"),
                    results.getString("last_name"),
                    results.getString("gender").toLowerCase().equals("M") ? Gender.M : Gender.F,
                    results.getDate("birth_date").toLocalDate(),
                    results.getDate("hire_date").toLocalDate()
            ));
        }
        db.releaseStatement(statement);
        return employees;
    }
    public List<Department> getDepartments() throws SQLException{
        if(getAllDepts == null){
            getAllDepts = db.getConnection().prepareStatement("SELECT * FROM departments");
        }
        ResultSet results = getAllDepts.executeQuery();
        List<Department> depts = new ArrayList<>();
        while(results.next()){
            depts.add(new Department(
                results.getString("dept_no"),
                results.getString("dept_name")));
        }
        return depts;
    }
    public void changeOffset(int value){
        offset += value;
        if(offset < 0) offset = 0;
    }
}
