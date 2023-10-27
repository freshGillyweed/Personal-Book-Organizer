package model;

import org.json.JSONObject;

// Represents a book having an author, title, genre, and totalPages
public class Book {
    private String author;
    private String title;
    private String genre;
    private int totalPages;

    // REQUIRES: totalPages > 1
    public Book(String author, String title, String genre, int totalPages) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.totalPages = totalPages;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    String getGenre() {
        return this.genre;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Title", title);
        json.put("Author", author);
        json.put("Genre", genre);
        json.put("Total pages", totalPages);
        return json;
    }
}
