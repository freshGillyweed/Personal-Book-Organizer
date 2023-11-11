package model;

import org.json.JSONObject;

// represents a book review having a book, rating,
public class BookReview {
    private Book book;
    private double rating;
    private String review;

    public BookReview(Book book) {
        this.book = book;
        this.rating = 0.0;
        this.review = "No review yet";
    }

    // MODIFIES: this
    // EFFECTS: adds/updates a rating for a certain book
    public void updateRating(double myRating) {
        this.rating = myRating;
    }


    // MODIFIES: this
    // EFFECTS: adds/updates the review of a book
    public void updateReview(String myReview) {
        this.review = myReview;
    }

    // EFFECTS: returns the reviewed book
    public Book getBook() {
        return this.book;
    }

    // EFFECTS: returns the rating of a reviewed book
    public double getRating() {
        return this.rating;
    }

    // EFFECTS: returns the review of a reviewed book
    public String getReview() {
        return this.review;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Title", book.getTitle());
        json.put("Author", book.getAuthor());
        json.put("Genre", book.getGenre());
        json.put("Rating", getRating());
        json.put("Review", getReview());
        json.put("Total pages", book.getTotalPages());
        return json;
    }
}
