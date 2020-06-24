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
 * @author gotped17
 */
public class Employee {

    private static final DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private String firstname;
    private String lastname;
    private Gender gender;
    private LocalDate birthdate;
    private LocalDate hiredate;

    public Employee(String firstname, String lastname, Gender gender, LocalDate birthdate, LocalDate hiredate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.hiredate = hiredate;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public LocalDate getHiredate() {
        return hiredate;
    }

    public void setHiredate(LocalDate hiredate) {
        this.hiredate = hiredate;
    }
    
    public Object[] convertToTableData(){
        Object[] array = new Object[4];
        array[0] = String.format("%s, %s", lastname, firstname);
        array[1] = gender.toString();
        array[2] = birthdate.format(dtf);
        array[3] = hiredate.format(dtf);
        return array;
    }
    
}
