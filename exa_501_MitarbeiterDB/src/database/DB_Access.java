/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Employee;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.String;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.postgresql.util.PSQLException;

/**
 *
 * @author Gottl
 */
public class DB_Access {

    private Connection connection;

    public DB_Access() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }

    public void connect(String dbName) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/" + dbName;
        String user = "postgres";
        String pw = "postgres";

        connection = DriverManager.getConnection(url, user, pw);
    }

    public void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public boolean createDB() throws SQLException {
        String dbName = "mitarbeiterdb";
        boolean exists = false;
        Statement statement = connection.createStatement();
        try {
            statement.executeUpdate("CREATE DATABASE " + dbName.toLowerCase());
            statement.close();
        } catch (PSQLException ex) {
            exists = true;
            statement.execute("DROP DATABASE mitarbeiterdb");
            statement.close();
            statement.close();
            createDB();
        }
        disconnect();
        connect(dbName.toLowerCase());
        return exists;
    }

    public boolean createTable() throws SQLException, IOException {
        String query
                = "CREATE TABLE mitarbeiter("
                + "pers_nr INTEGER PRIMARY KEY,"
                + "name VARCHAR(40) NOT NULL,"
                + "vorname VARCHAR(40) NOT NULL,"
                + "geb_datum DATE,"
                + "gehalt NUMERIC(7,2),"
                + "abt_nr INTEGER NOT NULL,"
                + "geschlecht CHAR(1) NOT NULL)";
        Statement statement = connection.createStatement();
        statement.execute(query);
        statement.close();

        File file = Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator + "res" + File.separator + "mitarbeiter_sql_inserts.sql").toFile();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String insertQuery = "";
        String line = br.readLine();
        line = br.readLine();
        line = br.readLine();
        while ((line = br.readLine()) != null) {
            insertQuery += line;
        }

        System.out.println(insertEmployee(insertQuery));
        return false;
    }

    public List<Employee> getEmployeesFromDepartment(int dept) throws SQLException {
        List<Employee> employees = getEmployees();
        List<Employee> filtered = new ArrayList<>();

        for (Employee employee : employees) {
            if (employee.getAbt_nr() == dept) {
                filtered.add(employee);
            }
        }
        return filtered;
    }

    public List<Employee> getEmployees() throws SQLException {
        String query = "SELECT * FROM mitarbeiter ORDER BY name ASC, vorname ASC";
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery(query);
        List<Employee> employees = new ArrayList<>();
        while (results.next()) {
            employees.add(new Employee(
                    results.getInt("pers_nr"),
                    results.getString("name"),
                    results.getString("vorname"),
                    results.getDate("geb_datum").toLocalDate(),
                    results.getBigDecimal("gehalt").doubleValue(),
                    results.getInt("abt_nr"),
                    results.getString("geschlecht").charAt(0)));
        }
        return employees;
    }

    public double getAverageSalary(char gender) throws SQLException {
        String query = "SELECT ROUND(AVG(gehalt), 2) as average "
                + "FROM mitarbeiter "
                + "WHERE geschlecht = '" + gender + "'";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        rs.next();
        double average = rs.getBigDecimal("average").doubleValue();
        statement.close();
        return average;
    }

    public boolean insertEmployee(Employee employee) throws SQLException {
        int number = findPersNr();
        employee.setPers_nr(number);
        
        Statement statement = connection.createStatement();
//"Values(%d, '%s', '%s', %s, %f, %d, '%c')
        String query = String.format("INSERT INTO mitarbeiter\n"
                + "VALUES(%d, '%s', '%s', '%s', %s, %d, '%c')", employee.getPers_nr(), employee.getName(), employee.getFirstname(),
                Date.valueOf(employee.getDateOfBirth()), employee.getGehalt()+"", employee.getAbt_nr(), employee.getGeschlecht());
        statement.executeUpdate(query);
        statement.close();
        return true;
    }

    public boolean insertEmployee(String query) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (PSQLException ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removeEmployee(Employee employee) throws SQLException {
        String query = "DELETE FROM mitarbeiter WHERE pers_nr = " + employee.getPers_nr();
        Statement statement = connection.createStatement();
        statement.execute(query);
        statement.close();
        return true;
    }

    public int findPersNr() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT pers_nr FROM mitarbeiter");
        int cnt = 10000;
        List<Integer> numbers = new ArrayList<>();
        while (rs.next()) {
            numbers.add(rs.getInt("pers_nr"));
        }
        do {
            if (numbers.contains(cnt)) {
                cnt++;
            } else {
                break;
            }
        } while (true);
        return cnt;
    }

    public static void main(String[] args) {
        try {
            DB_Access dba = new DB_Access();
            dba.connect("postgres");
            System.out.println(dba.createDB());
            dba.createTable();
            System.out.println(dba.getAverageSalary('M'));
            System.out.println(dba.getAverageSalary('F'));

            dba.getEmployeesFromDepartment(1);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB_Access.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DB_Access.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DB_Access.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
