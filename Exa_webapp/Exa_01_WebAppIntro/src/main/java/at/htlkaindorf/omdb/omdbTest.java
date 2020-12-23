/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.omdb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gottl
 */
public class omdbTest {
    
    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.omdbapi.com/?i=tt3896198&apikey=610a1d81");
            JsonMapper mapper = new JsonMapper();
            JsonNode node = mapper.readTree(url);
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node.toPrettyString());
            System.out.println(json);
        } catch (MalformedURLException ex) {
            Logger.getLogger(omdbTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(omdbTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
