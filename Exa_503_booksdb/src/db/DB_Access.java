/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import beans.Book;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gottl
 */
public class DB_Access {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DB_Access dbInstance = null;
    private DB_Database db;
    private PreparedStatement getAllBooks;
    private PreparedStatement getBookAuthors;
    private PreparedStatement getAllPublisher;
    private PreparedStatement getAllAuthors;
    private PreparedStatement getGenresForBook;
    private PreparedStatement getBookGenres;
    private PreparedStatement getAllGenres;
    private PreparedStatement getPublisherGenres;

    public static DB_Access getInstance() {
        if (dbInstance == null) {
            dbInstance = new DB_Access();
        }
        return dbInstance;
    }

    private DB_Access() {
        try {
            db = new DB_Database();
            db.connect();

        } catch (ClassNotFoundException ex) {
            System.out.println("An error occured while connecting to the database");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("An error occured while performing SQL operations");
            ex.printStackTrace();
        }
    }

    public List<Book> getBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        if (getAllBooks == null) {
            getAllBooks = db.getConnection().prepareStatement("SELECT * FROM books ORDER BY title ASC;");
        }
        ResultSet results = getAllBooks.executeQuery();
        while (results.next()) {
            books.add(new Book(
                    results.getString("title"),
                    results.getInt("total_pages"),
                    results.getBigDecimal("rating").floatValue(),
                    results.getString("isbn"),
                    getBookAuthors(results.getInt("book_id")),
                    getPublisher().get(results.getInt("publisher_id")),
                    getGenreForBook(results.getInt("book_id")),
                    results.getDate("published_date") == null ? null : results.getDate("published_date").toLocalDate()
            ));
        }
        return books;
    }

    private List<String> getBookAuthors(int bookID) throws SQLException {
        List<String> authors = new ArrayList<>();
        if (getBookAuthors == null) {
            getBookAuthors = db.getConnection().prepareStatement(
                    "SELECT b.book_id, a.first_name, a.middle_name, a.last_name\n"
                    + "FROM book_authors b INNER JOIN authors a ON b.author_id = a.author_id\n"
                    + "WHERE b.book_id = ?;");
        }
        getBookAuthors.setInt(1, bookID);
        ResultSet results = getBookAuthors.executeQuery();
        while (results.next()) {
            authors.add(String.format("%s, %s %s", results.getString("last_name"),
                    results.getString("first_name"),
                    results.getString("middle_name") == null ? "" : results.getString("middle_name")));
        }
        return authors;

    }

    private String getGenreForBook(int bookId) throws SQLException {
        if (getGenresForBook == null) {
            getGenresForBook = db.getConnection().prepareStatement(
                    "SELECT genre, book_id "
                    + "FROM genres INNER JOIN book_genres ON genres.genre_id = book_genres.genre_id "
                    + "WHERE book_id = ?;");
        }
        getGenresForBook.setInt(1, bookId);
        ResultSet results = getGenresForBook.executeQuery();
        if (results.next()) {
            return results.getString("genre");
        }
        return null;
    }

    public Map<Integer, String> getGenres() throws SQLException {
        Map<Integer, String> genres = new TreeMap<>();
        if (getAllGenres == null) {
            getAllGenres = db.getConnection().prepareStatement("SELECT * FROM genres");
        }
        ResultSet results = getAllGenres.executeQuery();
        while (results.next()) {
            genres.put(results.getInt("genre_id"), results.getString("genre"));
        }
        return genres;
    }

    public Map<Integer, String> getPublisher() throws SQLException {
        Map<Integer, String> publishers = new TreeMap<>();
        if (getAllPublisher == null) {
            getAllPublisher = db.getConnection().prepareStatement("SELECT * FROM publishers");
        }
        ResultSet results = getAllPublisher.executeQuery();
        while (results.next()) {
            publishers.put(results.getInt("publisher_id"), results.getString("name"));
        }
        return publishers;
    }

    public List<String> getGenresOfPublisher(int publisherId) throws SQLException {
        if (getPublisherGenres == null) {
            getPublisherGenres = db.getConnection().prepareStatement(
                    "SELECT DISTINCT p.name, g.genre "
                    + "FROM publishers p "
                    + "INNER JOIN books b ON b.publisher_id = p.publisher_id "
                    + "INNER JOIN book_genres bg ON bg.book_id = b.book_id "
                    + "INNER JOIN genres g ON g.genre_id = bg.genre_id "
                    + "WHERE p.publisher_id = ? "
                    + "ORDER BY g.genre;");
        }
        getPublisherGenres.setInt(1, publisherId);
        ResultSet results = getPublisherGenres.executeQuery();
        List<String> genres = new ArrayList<>();
        while (results.next()) {
            if (!results.getString("genre").equals("genres")) {
                genres.add(results.getString("genre"));
            }

        }
        return genres;
    }

    public List<String> getAuthors() throws SQLException {
        List<String> authors = new ArrayList<>();
        if (getAllAuthors == null) {
            getAllAuthors = db.getConnection().prepareStatement("SELECT * FROM authors;");
        }
        ResultSet results = getAllAuthors.executeQuery();
        while (results.next()) {
            authors.add(String.format("%s, %s %s", results.getString("last_name"),
                    results.getString("first_name"),
                    results.getString("middle_name")));

        }
        return authors;
    }

    public Map<Integer, Integer> getBookGenres() throws SQLException {
        Map<Integer, Integer> bookgenres = new TreeMap<>();
        if (getBookGenres == null) {
            getBookGenres = db.getConnection().prepareStatement("SELECT * FROM book_genres");
        }
        ResultSet results = getBookGenres.executeQuery();
        while (results.next()) {
            bookgenres.put(results.getInt("book_id"), results.getInt("genre_id"));
        }
        return bookgenres;
    }

    public List<Book> getBooksCustom(String query) throws SQLException {
        List<Book> books = new ArrayList<>();
        Statement statement = db.getStatement();
        ResultSet results = statement.executeQuery(query);
        if (!results.next()) {
            throw new RuntimeException("The query returned no values");
        } else {
            books.add(new Book(
                        results.getString("title"),
                        results.getInt("total_pages"),
                        results.getBigDecimal("rating").floatValue(),
                        results.getString("isbn"),
                        getBookAuthors(results.getInt("book_id")),
                        getPublisher().get(results.getInt("publisher_id")),
                        getGenreForBook(results.getInt("book_id")),
                        results.getDate("published_date") == null ? null : results.getDate("published_date").toLocalDate()
                ));
            while (results.next()) {
                books.add(new Book(
                        results.getString("title"),
                        results.getInt("total_pages"),
                        results.getBigDecimal("rating").floatValue(),
                        results.getString("isbn"),
                        getBookAuthors(results.getInt("book_id")),
                        getPublisher().get(results.getInt("publisher_id")),
                        getGenreForBook(results.getInt("book_id")),
                        results.getDate("published_date") == null ? null : results.getDate("published_date").toLocalDate()
                ));
            }
        }
        return books;
    }

    public void disconnect() {
        try {
            db.disconnect();
        } catch (SQLException ex) {
            System.out.println("An error occured while disconnecting from the database");
        }
    }

    public static void main(String[] args) {
        try {
            DB_Access db = DB_Access.getInstance();
            System.out.println(db.getBooks().toString());
            System.out.println(db.getGenres().values().toString());
            System.out.println(db.getPublisher().values().toString());
            System.out.println(db.getAuthors().toString());

        } catch (SQLException ex) {
            Logger.getLogger(DB_Access.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
