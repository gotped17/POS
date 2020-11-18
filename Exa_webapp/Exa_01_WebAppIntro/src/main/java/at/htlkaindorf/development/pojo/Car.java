/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.development.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 *
 * @author Gottl
 */

@Data // Shortcut für @Getter @Setter @ToString @EqualsAndHashcode
@AllArgsConstructor //Constructor mit allen Parametern
@NoArgsConstructor // Constroctor mit keinen Parametern
public class Car {    
    private String brand;
    private String type;
    @EqualsAndHashCode.Exclude //Excluded die Variabler aus der equals und hashcode methode
    @ToString.Exclude //Excluded die Variable aus der toString methode
    private double weight;
    
    public static void main(String[] args) {
        Car car = new Car();
        Car car2 = new Car("vw", "polo", 1234);
        System.out.println(car2.toString());
    }
}
