/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Gottl
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    private String name;
    private String stockmarket;
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Contact> contacts;

    public Company(String name, String stockmarket){
        this.name = name;
        this.stockmarket = stockmarket;
    }
    public boolean isName(String name){
        return this.name.toUpperCase().contains(name.toUpperCase());
    }
    @Override
    public String toString(){
        return String.format("%s(%s)", name, stockmarket);
    }
}
    