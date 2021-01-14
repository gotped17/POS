/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.beans;

import at.htlkaindorf.JSON.ListDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Gottl
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    @JsonProperty("imdbID")
    private String id;
    
    @JsonProperty("Title")
    private String title;
    
    @JsonProperty("Released")
    private String releaseRaw;
    
    @JsonProperty("Runtime")
    private String runtime;
    
    @JsonProperty("Rated")
    private String rating;
    @JsonProperty("Genre")
    
    @JsonDeserialize(using = ListDeserializer.class)
    private List<String> genres;
    
    @JsonProperty("Plot")
    private String plot;
    
    @JsonProperty("Poster")
    private String poster;
    
    public LocalDate getDateFormatted(){
        return LocalDate.parse(releaseRaw, DTF);
    }
    
    public String getGenreString(){
        return genres.stream().collect(Collectors.joining(", "));
    }
}
