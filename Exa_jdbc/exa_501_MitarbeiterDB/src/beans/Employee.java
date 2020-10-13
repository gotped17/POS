/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.sql.Date;

/**
 *
 * @author Gottl
 */
public class Employee {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private int pers_nr;
    private String name;
    private String firstname;
    private LocalDate dateOfBirth;
    private double gehalt;
    private int abt_nr;
    private char geschlecht;

    public Employee() {
    }

    public Employee(int pers_nr, String name, String firstname, LocalDate dateOfBirth, double gehalt, int abt_nr, char geschlecht) {
        this.pers_nr = pers_nr;
        this.name = name;
        this.firstname = firstname;
        this.dateOfBirth = dateOfBirth;
        this.gehalt = gehalt;
        this.abt_nr = abt_nr;
        this.geschlecht = geschlecht;
    }

    public int getPers_nr() {
        return pers_nr;
    }

    public void setPers_nr(int pers_nr) {
        this.pers_nr = pers_nr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getGehalt() {
        return gehalt;
    }

    public void setGehalt(double gehalt) {
        this.gehalt = gehalt;
    }

    public int getAbt_nr() {
        return abt_nr;
    }

    public void setAbt_nr(int abt_nr) {
        this.abt_nr = abt_nr;
    }

    public char getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(char geschlecht) {
        this.geschlecht = geschlecht;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%lf,%d,%c", pers_nr, name, firstname, dateOfBirth.format(DTF), gehalt, abt_nr, geschlecht);
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.dateOfBirth, other.dateOfBirth)) {
            return false;
        }
        return true;
    }

    public Object[] convertToTableData() {
        Object[] arr = new Object[7];
        arr[0] = pers_nr;
        arr[1] = name;
        arr[2] = firstname;
        arr[3] = DTF.format(dateOfBirth);
        arr[4] = gehalt;
        arr[5] = abt_nr;
        arr[6] = geschlecht;
        return arr;
    }
}
