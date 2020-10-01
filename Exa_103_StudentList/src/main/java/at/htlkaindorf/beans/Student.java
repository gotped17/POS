/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.beans;

import java.time.LocalDate;

/**
 *
 * @author Gottl
 */
public class Student {
    private String Nachname;
    private String Vorname;
    private String Klasse;
    private int KatNr;
    private LocalDate Birthdate;
    private char Geschlecht;

    public Student() {
    }

    public Student(String Nachname, String Vorname, String Klasse, int KatNr, LocalDate Birthdate, char Geschlecht) {
        this.Nachname = Nachname;
        this.Vorname = Vorname;
        this.Klasse = Klasse;
        this.KatNr = KatNr;
        this.Birthdate = Birthdate;
        this.Geschlecht = Geschlecht;
    }

    public String getNachname() {
        return Nachname;
    }

    public void setNachname(String Nachname) {
        this.Nachname = Nachname;
    }

    public String getVorname() {
        return Vorname;
    }

    public void setVorname(String Vorname) {
        this.Vorname = Vorname;
    }

    public String getKlasse() {
        return Klasse;
    }

    public void setKlasse(String Klasse) {
        this.Klasse = Klasse;
    }

    public int getKatNr() {
        return KatNr;
    }

    public void setKatNr(int KatNr) {
        this.KatNr = KatNr;
    }

    public LocalDate getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(LocalDate Birthdate) {
        this.Birthdate = Birthdate;
    }

    public char getGeschlecht() {
        return Geschlecht;
    }

    public void setGeschlecht(char Geschlecht) {
        this.Geschlecht = Geschlecht;
    }

    @Override
    public String toString() {
        return "Student{" + "Nachname=" + Nachname + ", Vorname=" + Vorname + ", Klasse=" + Klasse + ", KatNr=" + KatNr + ", Birthdate=" + Birthdate + ", Geschlecht=" + Geschlecht + '}';
    }
    
}
