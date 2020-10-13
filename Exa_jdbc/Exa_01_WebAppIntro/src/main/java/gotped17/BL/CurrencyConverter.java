/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gotped17.BL;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Gottl
 */
public class CurrencyConverter {
    private static Map<String, Double> Kurse = new HashMap<>();
    
    public static void initMap(){
        Kurse.put("Dollar", 1.19);
        Kurse.put("Pounds", 0.92);
        Kurse.put("Yen", 123.8);
        Kurse.put("Schilling", 13.76);
        Kurse.put("Bitcoin", 0.00011);
    }
    
    public static double convertFromEuro(double amount, String currency){
        if(Kurse.isEmpty()) initMap();
        if(!Kurse.keySet().contains(currency))
            throw new IllegalArgumentException("This currency is not supported");
        return amount * Kurse.get(currency);
    }
}
