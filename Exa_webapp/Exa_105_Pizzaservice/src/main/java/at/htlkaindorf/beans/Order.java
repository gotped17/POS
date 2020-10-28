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
public class Order {
    private Pizza pizza;
    private int count;

    public Order() {
    }

    public Order(Pizza pizza, int count) {
        this.pizza = pizza;
        this.count = count;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
}
