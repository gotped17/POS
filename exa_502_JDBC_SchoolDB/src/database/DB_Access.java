/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Gottl
 */
public class DB_Access {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DB_Access dbInstance = null;
    private DB_Database db;
    private Map<String, Integer> classMap = new TreeMap<>();
    private String insertStudentString = "INSERT INTO student(classid, catno, firstname, surname, gender, dateofbirth) "
            + "VALUES(?, ?, ?, ?, ?, ?)";
    private String insertClassString = "INSERT INTO grade(classname) VALUES(?)";
    private String getAllClasses = "SELECT * FROM grade";
    private String getAllStudents = "SELECT * FROM student ORDER BY classid, catno";
    private PreparedStatement getAllClassesStat;
    private PreparedStatement insertGradeStat;
    private PreparedStatement insertStudentStat;
    private PreparedStatement getAllStudentsStat;

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

    public void insertNewStudent(Student student) throws SQLException {
        if (insertStudentStat == null) {
            insertStudentStat = db.getConnection().prepareStatement(insertStudentString);
        }
        insertStudentStat.setInt(1, student.getClassid());
        insertStudentStat.setInt(2, student.getCatno());
        insertStudentStat.setString(4, student.getFirstname());
        insertStudentStat.setString(3, student.getSurname());
        insertStudentStat.setString(5, student.getGender() + "");
        insertStudentStat.setDate(6, Date.valueOf(student.getDateofbirth()));
        insertStudentStat.executeUpdate();
        updateCatno(student.getClassid());
    }

    public void insertNewClass(String classname) throws SQLException {
        if (insertGradeStat == null) {
            insertGradeStat = db.getConnection().prepareStatement(insertClassString);
        }
        insertGradeStat.setString(1, classname);
        insertGradeStat.executeUpdate();
    }

    public void insertValues() throws IOException, SQLException {
        clearTables();

        if (insertStudentStat == null) {
            insertStudentStat = db.getConnection().prepareStatement(insertStudentString);
        }
        if (insertGradeStat == null) {
            insertGradeStat = db.getConnection().prepareStatement(insertClassString);
        }
        List<String[]> splittedLines = readFile();

        for (String classname : classMap.keySet()) {
            insertGradeStat.setString(1, classname);
            insertGradeStat.executeUpdate();
        }

        getAllClasses();
        String oldClass = splittedLines.get(0)[0];

        int catno = 0;
        for (String[] splittedLine : splittedLines) {
            if (oldClass.equals(splittedLine[0])) {
                catno++;
            } else {
                catno = 1;
                oldClass = splittedLine[0];
            }

            Student student = parseStudent(splittedLine, catno);
            insertStudentStat.setInt(1, student.getClassid());
            insertStudentStat.setInt(2, student.getCatno());
            insertStudentStat.setString(4, student.getFirstname());
            insertStudentStat.setString(3, student.getSurname());
            insertStudentStat.setString(5, student.getGender() + "");
            insertStudentStat.setDate(6, Date.valueOf(student.getDateofbirth()));
            insertStudentStat.executeUpdate();
        }
    }

    public Map<String, Integer> getAllClasses() throws SQLException {

        classMap.clear();
        if (getAllClassesStat == null) {
            getAllClassesStat = db.getConnection().prepareStatement(getAllClasses);
        }
        ResultSet classes = getAllClassesStat.executeQuery();
        while (classes.next()) {
            classMap.put(classes.getString("classname"), classes.getInt("classid"));
        }
        return classMap;
    }

    public List<Student> getStudents() throws SQLException {

        List<Student> students = new ArrayList<>();
        if (getAllStudentsStat == null) {
            getAllStudentsStat = db.getConnection().prepareStatement(getAllStudents);
        }
        ResultSet studentData = getAllStudentsStat.executeQuery();

        while (studentData.next()) {
            students.add(new Student(
                    studentData.getInt("studentid"),
                    studentData.getInt("classid"),
                    studentData.getInt("catno"),
                    studentData.getString("firstname"),
                    studentData.getString("surname"),
                    studentData.getString("gender").charAt(0),
                    studentData.getDate("dateofbirth").toLocalDate()
            ));
        }

        return students;
    }

    public void exportToFile() throws SQLException, IOException {
        List<Student> students = getStudents();
        getAllClasses();
        Path path = Paths.get(System.getProperty("user.dir"), "src", "res", "students.csv");

        BufferedWriter bw = new BufferedWriter(new FileWriter(path.toFile()));
        bw.write("");
        for (Student student : students) {
            bw.append(student.toString() + "\n");
        }
        bw.close();
        //Collections.sort(students);
        path = Paths.get(System.getProperty("user.dir"), "src", "res", "grades.csv");
        bw = new BufferedWriter(new FileWriter(path.toFile()));
        bw.write("");
        Set<String> keys = classMap.keySet();
        for (String key : keys) {
            bw.append(classMap.get(key) + "," + key + "\n");
        }
        bw.close();

    }
    //Use this method always before ending the program
    public void tidyUp() throws SQLException {
        db.disconnect();
        db = null;
        
    }

    private Student parseStudent(String[] tokens, int catno) {
        return new Student(
                0,
                classMap.get(tokens[0]),
                catno,
                tokens[1],
                tokens[2],
                tokens[3].charAt(0),
                LocalDate.parse(tokens[4], DTF)
        );
    }

// <editor-fold defaultstate="collapsed" desc="unused code">  
/*    public void removeStudent(Student student) throws SQLException {
//        Statement stat = db.getStatement();
//        String query = "DELETE FROM student WHERE studentid = " + student.getStudentId();
//        stat.executeUpdate(query);
//        updateCatno(student.getClassid());
//        db.releaseStatement(stat);
    }*/
// </editor-fold>
    
    private void updateCatno(int classid) throws SQLException {
        Statement stat = db.getStatement();
        List<Student> studentList = new ArrayList<>();
        
        String query = "SELECT * FROM student WHERE classid = " + classid;
        ResultSet students = stat.executeQuery(query);
        while (students.next()) {
            studentList.add(new Student(
                    students.getInt("studentid"),
                    students.getInt("classid"),
                    students.getInt("catno"),
                    students.getString("firstname"),
                    students.getString("surname"),
                    students.getString("gender").charAt(0),
                    students.getDate("dateofbirth").toLocalDate()));
        }
        Collections.sort(studentList);
        int oldCatNo = 0;
        int newCatNo = oldCatNo+1;
        for (Student student : studentList) {
            if(student.getCatno()-1 != oldCatNo){
                student.setCatno(newCatNo);
            }
            oldCatNo++;
            newCatNo = oldCatNo+1;
            query = "UPDATE student SET catno = "+student.getCatno() + " WHERE studentid = " + student.getStudentId();
            stat.executeUpdate(query);
        }
        
        db.releaseStatement(stat);
    }

    private void clearTables() throws SQLException {

        Statement stat = db.getStatement();
        String query = "DELETE FROM student";
        int numRows = stat.executeUpdate(query);
        System.out.println("Deleted " + numRows + " students");
        query = "DELETE FROM grade";
        numRows = stat.executeUpdate(query);
        System.out.println("Deleted " + numRows + " grades");
        db.releaseStatement(stat);

    }

    private List<String[]> readFile() throws IOException {
        Path path = Paths.get(System.getProperty("user.dir"), "src", "res", "Studentlist_3XHIF.csv");
        List<String[]> splittedLines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path.toFile()));
        String line = br.readLine();
        while ((line = br.readLine()) != null) {
            splittedLines.add(line.split(";"));
        }
        getRawClasses(splittedLines);
        br.close();
        return splittedLines;
    }

    private void getRawClasses(List<String[]> splittedLines) {

        String oldClass = splittedLines.get(0)[0];
        classMap.put(oldClass, 0);
        splittedLines.stream()
                .filter((splittedLine) -> (!splittedLine[0].equals(oldClass)))
                .forEachOrdered((splittedLine) -> {
                    classMap.put(splittedLine[0], 0);
                }
                );
    }

   
    
    public static void main(String[] args) {
        try {
            Student test = new Student(3111,213,5,"ADAM","Gernot",'m',LocalDate.parse("2003-03-15", DTF));
            DB_Access db = getInstance();
            //db.insertValues();
            //db.removeStudent(test);
            //db.insertNewStudent(test);
            db.exportToFile();
            db.tidyUp();

        } catch (IOException ex) {
            System.out.println("An error occured while FileIO operations were performed");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("An error occured while SQL operations were performed");
            ex.printStackTrace();
        }

    }

}
