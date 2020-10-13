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
    private String Preis;
    private String imagePath;

    public Pizza(String name, String Preis, String imageName) {
        this.name = name;
        this.Preis = Preis;
        this.imagePath = imageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreis() {
        return Preis;
    }

    public void setPreis(String Preis) {
        this.Preis = Preis;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    
}
