/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;

/**
 *
 * @author Gottl
 */
public class Selection {
    
    private Book book;
    private String genre;

    public Selection(Book book, String genres) {
        this.book = book;
        this.genre = genres;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getGenres() {
        return genre;
    }

    public void setGenres(String genres) {
        this.genre = genres;
    }

    @Override
    public String toString() {
        return String.format("%s\n------\n%s", book.toString(), genre);
    }
    
    
}
