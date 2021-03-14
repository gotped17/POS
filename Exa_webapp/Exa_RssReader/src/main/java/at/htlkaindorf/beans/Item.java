package at.htlkaindorf.beans;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gottl
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
 
    private String title;
    private String description;
    private String link;
    private String author;
    private String pubDate;
}
