/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Gottl
 */
public class Book {

    private String title;
    private int totalPages;
    private float rating;
    private String isbn;
    private List<String> authors;
    private String publisher;
    private String genre;
    private LocalDate publishedDate;

    public Book(String title, int totalPages, float rating, String isbn, List<String> authors, String publisher, String genre, LocalDate publishedDate) {
        this.title = title;
        this.totalPages = totalPages;
        this.rating = rating;
        this.isbn = isbn;
        this.authors = authors;
        this.publisher = publisher;
        this.genre = genre;
        this.publishedDate = publishedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public String toString() {
        return String.format("%s, %d, %s", title, totalPages, genre);
    }

}
