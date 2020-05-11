/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author Gottl
 */
public class Student implements Comparable<Student>{
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private int studentId;
    private int classid;
    private int catno;
    private String firstname;
    private String surname;
    private char gender;
    private LocalDate dateofbirth;
    

    public Student() {
    }

    public Student(int studentId, int classid, int catno, String firstname, String surname, char gender, LocalDate dateofbirth) {
        this.studentId = studentId;
        this.classid = classid;
        this.catno = catno;
        this.firstname = firstname;
        this.surname = surname;
        this.gender = gender;
        this.dateofbirth = dateofbirth;
    }

   

    public int getCatno() {
        return catno;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setCatno(int catno) {
        this.catno = catno;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (this.classid != other.classid) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.dateofbirth, other.dateofbirth)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return String.format("%d;%d;%2d;%s;%s;%c;%s", studentId, classid, catno, firstname, surname, gender, dateofbirth.format(DTF));
    }

    @Override
    public int compareTo(Student o) {
        int classDifference = classid - o.getClassid();
        int surnameComp = surname.compareToIgnoreCase(o.getSurname()) + classDifference;
        if(surnameComp == 0){
            
            return firstname.compareToIgnoreCase(o.getFirstname()) + classDifference;
        }
        return surnameComp;
    }
    
    
    
}
