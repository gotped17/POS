/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.beans;

import java.io.Serializable;

/**
 *
 * @author Gottl
 */
public class Pizza implements Serializable{
 
    private String name;
    private double price;
    private String base64;

    public Pizza() {}
    
    public Pizza(String name, double price, String imageName) {
        this.name = name;
        this.price = price;
        this.base64 = imageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
    
    
}
