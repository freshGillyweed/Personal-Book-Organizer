package model;

import org.json.JSONObject;

// represents a bookmark having a book and the page the user must resume from
public class Bookmark {
    private Book book;
    private int currentPage;

    public Bookmark(Book book) {
        this.book = book;
        this.currentPage = 1;
    }

    // REQUIRES: page > 1
    // MODIFIES: this
    // EFFECTS: the bookmark's page is updated
    //          to show which page the reader must resume reading
    public void updatePage(int page) {
        this.currentPage = page;
    }

    // EFFECTS: the book that is bookmarked is returned
    public Book getBook() {
        return this.book;
    }

    // EFFECTS: the page that the reader must resume on is returned
    public int getCurrentPage() {
        return this.currentPage;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Title", book.getTitle());
        json.put("Author", book.getAuthor());
        json.put("Genre", book.getGenre());
        json.put("Current page", currentPage);
        json.put("Total pages", book.getTotalPages());
        return json;
    }
}
