package model;

public class BookReview {
    private Book book;
    private double rating;
    private int numberOfTimesRead;
    private String review = null;

    public BookReview(Book book) {
        this.book = book;
        this.rating = 0.0;
        this.numberOfTimesRead = 0;
    }

    // MODIFIES: this
    // EFFECTS: adds/updates a rating for a certain book
    void updateRating(double myRating) {
        this.rating = myRating;
    }

    // MODIFIES: this
    // EFFECTS: increments the number of times a book has been read
    void updateNumberOfTimesRead() {
        this.numberOfTimesRead += 1;
    }

    // MODIFIES: this
    // EFFECTS: adds/updates the review of a book
    void updateReview(String myReview) {
        this.review = myReview;
    }

    // EFFECTS: returns the reviewed book
    Book getBook() {
        return this.book;
    }

    // EFFECTS: returns the rating of a reviewed book
    double getRating() {
        return this.rating;
    }

    // EFFECTS: returns the number of times a reviewed book was read
    int getNumberOfTimesRead() {
        return this.numberOfTimesRead;
    }

    // EFFECTS: returns the review of a reviewed book
    String getReview() {
        return this.review;
    }
}
