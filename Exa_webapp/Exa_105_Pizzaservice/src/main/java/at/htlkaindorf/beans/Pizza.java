/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.beans;

/**
 *
 * @author Gottl
 */
public class Pizza {
 
    private String name;
    private double Preis;
    private String base64;

    public Pizza(String name, double Preis, String imageName) {
        this.name = name;
        this.Preis = Preis;
        this.base64 = imageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPreis() {
        return Preis;
    }

    public void setPreis(double Preis) {
        this.Preis = Preis;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
    
    
}
