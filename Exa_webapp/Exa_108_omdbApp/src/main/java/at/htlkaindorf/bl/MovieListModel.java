/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.bl;

import at.htlkaindorf.JSON.JSONAccess;
import at.htlkaindorf.beans.Movie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Gottl
 */
public class MovieListModel {

    private List<Movie> movies;
    private List<Movie> filteredMovies;
    private int currentPage = 1;
    private Set<String> currentGenres;
    private Set<String> filterGenres;
    private int currentTotalResults;
    private JSONAccess json;

    public MovieListModel() {
        try {
            filteredMovies = new ArrayList<>();
            json = new JSONAccess();
            currentPage = 1;
            Map<Integer, List<String>> results = json.searchDatabase("guard", currentPage);
            currentTotalResults = (int) results.keySet().toArray()[0];
            filterGenres = new HashSet<>();
            currentGenres = new HashSet<>();
            movies = json.getMovies(results.get(currentTotalResults));
            // System.out.println(movies.toString());
            filteredMovies.addAll(movies);
            for (Movie movie : filteredMovies) {
                currentGenres.addAll(movie.getGenres());
            }
        } catch (IOException ex) {
            Logger.getLogger(MovieListModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchForFilms(String query){
        try {
            movies.clear();
            filteredMovies.clear();
            currentGenres.clear();
            currentPage=1;
            Map<Integer, List<String>> results = json.searchDatabase(query, currentPage);
            currentTotalResults = (int) results.keySet().toArray()[0];
            movies = json.getMovies(results.get(currentTotalResults));
            filteredMovies.addAll(movies);
            for (Movie movie : filteredMovies) {
                currentGenres.addAll(movie.getGenres());
            }
        } catch (IOException ex) {
            Logger.getLogger(MovieListModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getNumberOfResults() {
        return currentTotalResults;
    }

    public List<Movie> getMovies() {
        return filteredMovies;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void getNextPage() {

    }

    public void getPrevPage() {

    }

    public Set<String> getGenres() {
        return currentGenres;
    }

    public void filterList(String genre) {
        filteredMovies.clear();
        filterGenres.clear();
        if (genre.equals("All")) {
            filteredMovies.addAll(movies);
        } else {
            filterGenres.add(genre);
            filteredMovies.addAll(
                    movies.stream()
                            .filter(m -> !Collections.disjoint(m.getGenres(), filterGenres))
                            .collect(Collectors.toList())
            );
        }

    }
}
