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

    public String getGenre() {
        return this.genre;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Title", this.getTitle());
        json.put("Author", this.getAuthor());
        json.put("Genre", this.getGenre());
        json.put("Total pages", this.getTotalPages());
        return json;
    }
}
