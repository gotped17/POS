/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.development.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    // @EqualsAndHashCode.Exclude //Excluded die Variabler aus der equals und hashcode methode
    // @ToString.Exclude //Excluded die Variable aus der toString methode
    // @JsonIgnore // jackson ignoriert diese Variable beim writen
    private double weight;
    
    public static void main(String[] args) {
        Car car = new Car();
        Car car2 = new Car("vw", "polo", 1234);
        System.out.println(car2.toString());
        Car[] cars = {car, car2};
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonStr = mapper.writeValueAsString(cars);
            System.out.println(jsonStr);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
