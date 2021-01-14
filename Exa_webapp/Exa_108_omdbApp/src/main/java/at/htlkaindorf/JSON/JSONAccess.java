/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.JSON;

import at.htlkaindorf.beans.Movie;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gottl
 */
public class JSONAccess {
    private List<Movie> movies = new ArrayList<>();
    private JsonMapper json;
    private String urlBase;
    public JSONAccess(){
        json = new JsonMapper();
        urlBase = "http://www.omdbapi.com/?apikey=610a1d81";
    }
    public Map<Integer,List<String>> searchDatabase(String pattern, int page) throws MalformedURLException, IOException{
        System.out.println("searching database");
        List<String> movieIds = new ArrayList<>();
        String urlString = String.format("%s&s=%s&page=%d&type=movie", urlBase, pattern, page);
        URL url = new URL(urlString);
        JsonNode node = json.readTree(url);
        List<JsonNode> nodes = node.findValues("imdbID"); // List of the 10 IDs as nodes
            // System.out.println(node.get("totalResults")); // total number of results
            // System.out.println(nodes);
            for(int i = 0; i<10; i++){
                movieIds.add(nodes.get(i).toString());
            }
            Map result = new HashMap<>();
            result.put(node.get("totalResults").asInt(), movieIds);
            movies.clear();
        return result;
    }
    public List<Movie> getMovies(List<String> movieIds) throws MalformedURLException, IOException{
        System.out.println("retrieving data");
        if(movies.isEmpty()){
            for (String movieId : movieIds) {
                String urlString = String.format("%s&type=movie&i=%s", urlBase, movieId).replace("\"", "");
                // System.out.println(urlString);
                URL url = new URL(urlString);
                movies.add(json.readValue(url, Movie.class));
            }
        }
        return movies;
    }
    
    public void clearSearch(){
        movies.clear();
    }
    
    
}
