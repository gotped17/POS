/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Gottl
 */
public class Student {
 
    private int studentID;
    private int catalog_nr;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String klasse;

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public Student(){
        
    }
    public Student(int studentID, int catalog_nr, String firstname, String lastname, LocalDate birthdate) {
        this.studentID = studentID;
        this.catalog_nr = catalog_nr;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getCatalog_nr() {
        return catalog_nr;
    }

    public void setCatalog_nr(int catalog_nr) {
        this.catalog_nr = catalog_nr;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return String.format("%d, %d, %s %s %s", studentID, catalog_nr, firstname, lastname, birthdate.format(dtf));
    }
    
    
    
}
