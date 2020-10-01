/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gotped17.BL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Gottl
 */
public class CurrencyConverterTest {
    
    public CurrencyConverterTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }

    /**
     * Test of initMap method, of class CurrencyConverter.
     */

    /**
     * Test of convertFromEuro method, of class CurrencyConverter.
     */
    @org.junit.jupiter.api.Test
    public void testConvertFromEuro() {
        System.out.println("convertFromEuro");
        double amount = 1.0;
        String currency = "Schilling";
        double expResult = 13.76;
        double result = CurrencyConverter.convertFromEuro(amount, currency);
        assertEquals(expResult, result, 0.1);
    }
    @org.junit.jupiter.api.Test
    public void testConvertFromEuro2() {
        System.out.println("convertFromEuro");
        double amount = 1.0;
        String currency = "Bitcoin";
        double expResult = 0.00011;
        double result = CurrencyConverter.convertFromEuro(amount, currency);
        assertEquals(expResult, result, 0.1);
    }
    @org.junit.jupiter.api.Test
    public void testConvertFromEuroInvalid() {
        System.out.println("convertFromEuro");
        double amount = 1.0;
        String currency = "asfalsködfj";
        double expResult = 1.19;
        try{
            double result = CurrencyConverter.convertFromEuro(amount, currency);
            fail("This line should never be reached");
        }
        catch(IllegalArgumentException ex){
            assertEquals("This currency is not supported", ex.getMessage());
        }
        
        
    }
    
}
